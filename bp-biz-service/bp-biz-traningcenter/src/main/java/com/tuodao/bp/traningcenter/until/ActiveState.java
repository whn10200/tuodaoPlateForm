package com.tuodao.bp.traningcenter.until;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.springframework.beans.factory.annotation.Value;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Created by Administrator on 2017/10/17 0017.
 */
public class ActiveState {

    @Value("${activemq.broker-url}")
    private static String brokerUrl;

    @Value("${activemq.user}")
    private String user;

    @Value("${activemq.password}")
    private String password;

    /**
     * 获取状态
     * @throws Exception
     */
    public static void bbb() throws Exception {
        JMXServiceURL url = new JMXServiceURL(brokerUrl);
        JMXConnector connector = JMXConnectorFactory.connect(url, null);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        // 需要注意的是，这里的jms-broker必须和上面配置的名称相同
        ObjectName name = new ObjectName("1111"+":BrokerName=localhost,Type=Broker");
        BrokerViewMBean mBean =  (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(connection,
                name, BrokerViewMBean.class, true);
        // System.out.println(mBean.getBrokerName());

        for(ObjectName queueName : mBean.getQueues()) {
            QueueViewMBean queueMBean =  (QueueViewMBean)MBeanServerInvocationHandler
                    .newProxyInstance(connection, queueName, QueueViewMBean.class, true);
            System.out.println("\n------------------------------\n");

            // 消息队列名称
            System.out.println("States for queue --- " + queueMBean.getName());

            // 队列中剩余的消息数
            System.out.println("Size --- " + queueMBean.getQueueSize());

            // 消费者数
            System.out.println("Number of consumers --- " + queueMBean.getConsumerCount());

            // 出队数
            System.out.println("Number of dequeue ---" + queueMBean.getDequeueCount() );
        }

    }


}
