package jvm.gc;

/**
 * 介绍下JVM的一些常用参数
 * -Xms4096M -Xmx4096M -Xmn1536M -Xss1M
 *
 * -XX:+UseConcMarkSweepGC
 * -XX:+CMSParallelRemarkEnabled
 * -XX:+UseCMSInitiatingOccupancyOnly
 * -XX:CMSInitiatingOccupancyFraction=80
 * -XX:+UseCMSCompactAtFullCollection
 * -XX:CMSFullGCsBeforeCompaction=4
 * -XX:+UseParNewGC
 * -verbose:gc
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -Xloggc:/tmp/solar/dump
 * -XX:+UseGCLogFileRotation
 * -XX:NumberOfGCLogFiles=8
 * -XX:GCLogFileSize=128M
 * -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9081
 */
public class debug {
}
