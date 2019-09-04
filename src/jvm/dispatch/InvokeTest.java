package jvm.dispatch;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * "Human" 称为变量的静态类型（Static Type），或者叫做外观类型（Apparent Type），
 * "Man" 和 "Woman" 则称为变量的实际类型（Actual Type）
 *
 * 静态分派（Method Overload Resolution 方法重载决议）
 * 动态分派 Method Override Resolution 方法重载决议）
 * 单分派 多分派
 *
 * invokedynamic 指令与前面 4 条 “invoke*” 指令的最大差别就是它的分派逻辑不是由虚拟机决定的，而是由程序员决定。
 *
 * 下面的例子，帮助读者理解程序员在可以掌握方法分派规则之后，能做什么以前无法做到的事情。
 *
 * java.lang.invoke 与 java.lang.reflect 包的区别
 * 		· MethodHandle服务于所有java虚拟机上的语言		Reflection仅仅服务于java语言。
 * 		· MethodHandle在模拟字节码层次的方法调用		Reflection在模拟Java代码层次的调用。
 * 		· MethodHandle是轻量级						Reflection是重量级。
 * 		· MethodHandle可以进行内联优化				Reflection完全没有。
 *
 */
public class InvokeTest {

	class GrandFather {
		void thinking() {
			System.out.println("i am grandfather");
		}
	}
	
	class Father extends GrandFather {
		void thinking() {
			System.out.println("i am father");
		}
	}
	
	class Son extends Father {
		void thinking() {
			final String methodName = "thinking";
			try {
				MethodType methodType = MethodType.methodType(void.class);

				MethodHandles.Lookup lookup = MethodHandles.lookup();
				MethodHandle methodHandle = lookup.findSpecial(GrandFather.class, methodName, methodType, Son.class);

				methodHandle.invoke(this);
			} catch (Throwable e) { }
		}

		/**
		 * MPL_LOOKUP 是用来判断私有方法是否被信任的标识，用来控制访问权限的.默认是false,
		 * 默认情况下findSpecial方法中的最后一个参数Class<?> specialCaller 有一个校验 checkSpecialCaller,
		 * 如果当前的lookup的类和specialCaller不一致的话就会检测不通过,
		 * 		IMPL_LOOKUP.setAccessible(true);
		 * 		设置为true之后，(MethodHandles.Lookup) IMPL_LOOKUP.get(null)这是获取一个Lookup，
		 * 		这种方式返回的allowedModes为-1 这样的话就可以绕过检查，从而执行执行传入specialCaller类中的方法，
		 * 		当然也有风险，舍弃了强校验，很容易抛出NoSuchMethodError.
		 * */
		void thinking0() {
			final String methodName = "thinking";
			try {
				MethodType methodType = MethodType.methodType(void.class);

				Field IMPL_LOOKUP = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
				IMPL_LOOKUP.setAccessible(true);
				MethodHandles.Lookup lookup = (MethodHandles.Lookup) IMPL_LOOKUP.get(null);
				MethodHandle methodHandle = lookup.findSpecial(GrandFather.class, methodName, methodType, GrandFather.class);

				methodHandle.invoke(this);
			} catch (Throwable e) { }
		}
	}

	/**
	 * 在JDK1.7上, 运行结果是 I'm grandFather
	 * 在JDK1.8上, 运行结果是 I’m father
	 *
	 * https://docs.oracle.com/javase/8/docs/api/java/lang/invoke/MethodHandles.Lookup.html#findSpecial-java.lang.Class-java.lang.String-java.lang.invoke.MethodType-java.lang.Class
	 *
	 * https://www.zhihu.com/question/40427344
	 *
	 * JDK1.8环境下MethodHandles.lookup方法是调用者敏感的，不同调用者访问权限不同，其结果也不同。
	 *
	 * 有一种观点
	 * 		MethodHandle做的事，就是模拟并实现Java字节码里的一些指令比如: invokespecial, invokevirtual, invokestatic 等。
	 *
	 * 		所以， findSpecial->invoke 也要遵守JVM字节码里invokespecial指令的限制，
	 * 			也即 "它只能调用到传给findSpecial()方法的最后一个参数（“specialCaller”）的直接父类的版本。"
	 *
	 * 		至于为何JAVA7某些版本可以越过直接父类限制而直接调用到GrantFather的方法，
	 * 			"可能是因为findSpecial()得到的MethodHandle的具体语义在JSR 292的设计过程中有被调整过。
	 * 			有一段时间findSpecial()得到的MethodHandle确实可以超越invokespecial的限制去调用到任意版本的虚方法，但这种行为很快就被认为是bug而修正了。"
	 *
	 */
	public static void main(String[] args) {
		(new InvokeTest().new Son()).thinking();
	}
}
