# 原理
通过 init-container 将劫持者 envoy 注入到 pod 里；然后通过管理 iptables 转发流量。

# 术语
Sidecar 模式：容器应用模式之一，Service Mesh 架构的一种实现方式。
Init 容器：Pod 中的一种专用的容器，在应用程序容器启动之前运行，用来包含一些应用镜像中不存在的实用工具或安装脚本。
iptables：流量劫持是通过 iptables 转发实现的。