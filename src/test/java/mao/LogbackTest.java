package mao;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project name(项目名称)：logback_demo
 * Package(包名): mao
 * Class(类名): LogbackTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/10/31
 * Time(创建时间)： 18:56
 * Version(版本): 1.0
 * Description(描述)： 无
 */


public class LogbackTest
{
    //简单使用
    @Test
    public void test1()
    {
        Logger logger = LoggerFactory.getLogger("mao.logback.HelloWorld");
        logger.debug("debug ...");
    }

    //打印日志内部状态
    @Test
    public void test2()
    {
        Logger logger = LoggerFactory.getLogger("mao.logback.HelloWorld");
        logger.debug("debug ...");
        // 打印内部的状态
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }

    /*
     * 日志输出级别：ERROR > WARN > INFO > DEBUG > TRACE
     * */

    //测试默认的日志输出级别
    @Test
    public void test3()
    {
        Logger logger = LoggerFactory.getLogger("mao.logback.HelloWorld");
        logger.error("error ...");
        logger.warn("warn ...");
        logger.info("info ...");
        logger.debug("debug ...");
        //因为默认的输出级别为debug，所以这一条日志不会输出
        logger.trace("trace ...");
    }

    //设置日志输出级别
    @Test
    public void test4()
    {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("mao.logback.HelloWorld");
        logger.setLevel(Level.WARN);
        logger.error("error ...");
        logger.warn("warn ...");
        logger.info("info ...");
        logger.debug("debug ...");
        logger.trace("trace ...");
    }

    //测试Logger的继承
    @Test
    public void test5()
    {
        ch.qos.logback.classic.Logger logger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("mao");
        logger.setLevel(Level.INFO);
        logger.error("error ...");
        logger.warn("warn ...");
        logger.info("info ...");
        logger.debug("debug ...");
        logger.trace("trace ...");

        // "mao.logback" 会继承 "mao" 的有效级别
        Logger barLogger = LoggerFactory.getLogger("mao.logback");
        // 这条日志会打印，因为 INFO >= INFO
        barLogger.info("子级信息");
        // 这条日志不会打印，因为 DEBUG < INFO
        barLogger.debug("子级调试信息");
    }

    //Logger获取，根据同一个名称获得的logger都是同一个实例
    @Test
    public void test6()
    {
        Logger logger1 = LoggerFactory.getLogger("mao");
        Logger logger2 = LoggerFactory.getLogger("mao");
        System.out.println(logger1 == logger2);
    }

    //参数化日志
    @Test
    public void test7()
    {
        Logger logger = LoggerFactory.getLogger("mao");
        logger.debug("hello {}", "world");
    }
}
