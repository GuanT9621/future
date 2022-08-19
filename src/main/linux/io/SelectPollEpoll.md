# tips
我们最熟悉的句柄是0、1、2三个，0是标准输入，1是标准输出，2是标准错误输出。
0、1、2是整数表示的，对应的FILE *结构的表示就是 stdin、stdout、stderr。

# Select(2)
int select(int nfds, 
fd_set *restrict readfds, 
fd_set *restrict writefds, 
fd_set *restrict exceptfds,
struct timeval *restrict timeout);

函数解释：
int 返回值 
ret<0  说明select函数出错
ret==0 说明在我们设定的等待时间内，fd的状态没有发生变化
ret>0  说明在我们设定的等待时间内，fd的状态发生了变化
nfds        含义 next fd series 此参数为三组 fds 里编号最高的 fd 加 1。目的为内核确定需要监听的 fd 范围为 (0, nfds)。
readfds     含义 句柄 0 stdin 需要检测可读的 fd ；调用Select后会被清空
writefds    含义 句柄 1 stdout需要检测可写的 fd ；调用Select后会被清空
exceptfds   含义 句柄 2 stderr需要检测异常的 fd ；调用Select后会被清空
timeout     含义 超时时间

注：调用select()函数之后，select() 函数会清空它所检测的socket描述符集合，导致每次调用select()之前都必须把socket描述符重新加入到待检测的集合中；
因此，select()函数适合于只检测一个socket描述符的情况，而poll()函数适合于大量socket描述符的情况；

例子：
fd_set rdfds;           /* 先申明一个 fd_set 集合来保存我们要检测的 socket句柄 */
struct timeval tv;      /* 申明一个时间变量来保存时间 */
int ret;                /* 保存返回值 */
FD_ZERO(&rdfds);        /* 用select函数之前先把集合清零 */
FD_SET(socket, &rdfds); /* 把要检测的句柄socket加入到集合里 */
tv.tv_sec = 1;
tv.tv_usec = 500;       /* 设置select等待的最大时间为1秒加500毫秒 */
ret = select(socket + 1, &rdfds, NULL, NULL, &tv); /* 检测我们上面设置到集合rdfds里的句柄是否有可读信息 */
if(ret < 0) perror("select");                       /* 这说明select函数出错 */
else if(ret == 0) printf("超时\n");                 /* 说明在我们设定的时间值1秒加500毫秒的时间内，socket的状态没有发生变化 */
else {                                              /* 说明等待时间还未到1秒加500毫秒，socket的状态发生了变化 */
printf("ret=%d\n", ret);                            /* ret这个返回值记录了发生状态变化的句柄的数目，由于我们只监视了socket这一个句柄，所以这里一定ret=1，如果同时有多个句柄发生变化返回的就是句柄的总和了 */
/* 这里我们就应该从socket这个句柄里读取数据了，因为select函数已经告诉我们这个句柄里有数据可读 */
if(FD_ISSET(socket, &rdfds)) { /* 先判断一下socket这外被监视的句柄是否真的变成可读的了 */
recv(...); /* 读取socket句柄里的数据 */
}
}

# Poll(2)
int poll(struct pollfd *fds, nfds_t nfds, int timeout);
函数解释：
int 返回值
ret < 0 说明poll函数出错
ret==0  说明系统调用超时
ret > 0 说明poll函数成功时，代表 pollfds 中其 revents 字段的元素数
fds     含义一个 struct pollfd 结构类型的数组，用于存放需要检测其状态的Socket描述符；调用poll函数后不会清空这个fds数组
nfds    含义 nfds_t 类型的参数，用于标记数组 fds 中的结构体元素的总数量即数组有效长度。
timeout 含义是poll函数调用阻塞的超时时间，单位：毫秒

struct pollfd {
    int fd;
    short events; 
    short revents;
};
events  含义 作为函数的输入参数，指定应用程序对 fd 感兴趣的事件。 该字段可以指定为零，在这种情况下，可以在 revents 中返回的唯一事件是 POLLHUP POLLERR POLLNVAL
revents 含义 作为函数的输出参数，由内核填充实际发生的事件。 revents 中返回事件包含 events 的事件，或值 POLLERR POLLHUP POLLNVAL 之一

POLLIN 读
POLLPRI 异常
POLLOUT 写
POLLRDHUP 关闭连接
POLLERR 条件错误
POLLHUP 管道关闭/挂断
POLLNVAL 无效请求 FD未打开
POLLRDNORM 相当于POLLIN
POLLRDBAND 可以读取优先带数据（在 Linux 上通常不使用）
POLLWRNORM 相当于POLLOUT
POLLWRBAND 可以写入高优先级数据

注意：每当调用poll函数之后，系统不会清空这个 fds 数组，操作起来比较方便；特别是对于 socket连接比较多的情况下，在一定程度上可以提高处理的效率；
因此，select()函数适合于只检测一个socket描述符的情况，而poll()函数适合于大量socket描述符的情况；

## DIFF IN Select Poll
poll() 不要求用户计算编号最高的文件描述符 +1 的值；
poll() 对于大值文件描述符更有效。假设我们通过 select() 方法监视一个值为 900 的单个文件描述符 —— 内核将不得不检查传入集合的每个值的每一位，直到第 900 位；
select() 的文件描述符集合是静态大小的；
使用 select()，文件描述符集合会在返回时重建，因此每个后续调用都必须重新初始化它们。 poll() 系统调用将输入（events 字段）与输出（revents 字段）分隔开，允许在不更改的情况下重新使用该数组。
返回时，select() 的 timeout 参数未定义。 可移植性代码需要重新初始化它，这不是pselect() 的问题；
select() 更具可移植性，因为某些 Unix 系统不支持 poll()。

# Epoll(7)
Epoll* 系统调用帮助我们创建和管理内核中的上下文，我们将任务分为 3 个步骤：
使用 epoll_create 在内核中创建一个上下文；
使用 epoll_ctl 向/从上下文添加/移除文件描述符；
使用 epoll_wait 等待上下文中的事件。

## DIFF IN Epoll poll/select
我们可以在等待时添加或删除文件描述符；
epoll_wait 仅返回具有准备文件描述符的对象；
epoll 有更好的性能 —— O(1) 而不是O(n)；
epoll 可以表现为级别触发或边缘触发（请参见手册页）；
epoll 是 Linux 特有的，因此可移植性一般。

# MMAP(2)
 内存映射，简而言之就是将用户空间的一段内存区域映射到内核空间，映射成功后，用户对这段内存区域的修改可以直接反映到内核空间，同样，内核空间对这段区域的修改也直接反映用户空间。
 那么对于内核空间<---->用户空间两者之间需要大量数据传输等操作的话效率是非常高的。

mmap系统调用并不是完全为了用于共享内存而设计的。它本身提供了不同于一般对普通文件的访问方式，进程可以像读写内存一样对普通文件的操作。
mmap系统调用使得进程之间通过映射同一个普通文件实现共享内存。普通文件被映射到进程地址空间后，进程可以像访问普通内存一样对文件进行访问，不必再调用read()，write（）等操作。mmap并不分配空间, 只是将文件映射到调用进程的地址空间里（但是会占掉你的 virutal memory）, 然后你就可以用memcpy等操作写文件, 而不用write()了.写完后，内存中的内容并不会立即更新到文件中，而是有一段时间的延迟，你可以调用 msync()来显式同步一下, 这样你所写的内容就能立即保存到文件里了.这点应该和驱动相关。 不过通过mmap来写文件这种方式没办法增加文件的长度, 因为要映射的长度在调用mmap()的时候就决定了.如果想取消内存映射，可以调用 munmap()来取消内存映射

void *mmap(void *addr, 
size_t length, 
int prot, 
int flags,
int fd, 
off_t offset);

参数
addr    含义 要映射到的内存的起始地址，通常为NULL代表由内核指定
length  含义 要映射的内存的大小
prot    含义 期望的内存保护标志，不能与文件的打开模式冲突。可以通过or运算合理地组合在一起
flags   含义 指定映射对象的类型，映射选项和映射页是否可以共享。它的值可以多个位的组合体
fd      含义 文件描述符（由open函数返回）
offset  含义 表示被映射对象（即文件）从那里开始对映，通常都是用0。 该值应该为大小为PAGE_SIZE的整数倍

prot 
PROT_EXEC //页内容可以被执行
PROT_READ  //页内容可以被读取
PROT_WRITE //页可以被写入
ROT_NONE  //页不可访问

flags
MAP_FIXED ：使用指定的映射起始地址，如果由start和len参数指定的内存区重叠于现存的映射空间，重叠部分将会被丢弃。如果指定的起始地址不可用，操作将会失败。并且起始地址必须落在页的边界上。
MAP_SHARED ：对映射区域的写入数据会复制回文件内, 而且允许其他映射该文件的进程共享。
MAP_PRIVATE ：建立一个写入时拷贝的私有映射。内存区域的写入不会影响到原文件。这个标志和以上标志是互斥的，只能使用其中一个。
MAP_DENYWRITE ：这个标志被忽略。
MAP_EXECUTABLE ：同上
MAP_NORESERVE ：不要为这个映射保留交换空间。当交换空间被保留，对映射区修改的可能会得到保证。当交换空间不被保留，同时内存不足，对映射区的修改会引起段违例信号。
MAP_LOCKED ：锁定映射区的页面，从而防止页面被交换出内存。
MAP_GROWSDOWN ：用于堆栈，告诉内核VM系统，映射区可以向下扩展。
MAP_ANONYMOUS ：匿名映射，映射区不与任何文件关联。
MAP_ANON ：MAP_ANONYMOUS的别称，不再被使用。
MAP_FILE ：兼容标志，被忽略。
MAP_32BIT ：将映射区放在进程地址空间的低2GB，MAP_FIXED指定时会被忽略。当前这个标志只在x86-64平台上得到支持。
MAP_POPULATE ：为文件映射通过预读的方式准备好页表。随后对映射区的访问不会被页违例阻塞。
MAP_NONBLOCK ：仅和MAP_POPULATE一起使用时才有意义。不执行预读，只为已存在于内存中的页面建立页表入口。