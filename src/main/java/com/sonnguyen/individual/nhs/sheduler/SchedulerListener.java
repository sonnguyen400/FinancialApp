//package com.sonnguyen.individual.nhs.sheduler;
//
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.impl.StdSchedulerFactory;
//import org.quartz.spi.JobFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//@ApplicationScoped
//public class SchedulerListener implements ServletContextListener {
//    private JobFactory jobFactory;
//
//    private static final Logger log = LoggerFactory.getLogger(SchedulerListener.class);
//
//    @Inject
//    public SchedulerListener(final CDIJobFactory jobFactory) {
//        this.jobFactory = jobFactory;
//    }
//
//    @Override
//    public void contextInitialized(final ServletContextEvent servletEvent) {
//        System.out.println("Initializing Listener");
//        log.info("Initializing Listener");
//        try {
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            scheduler.setJobFactory(jobFactory);
//        } catch (final SchedulerException | RuntimeException schedEx) {
//            System.out.println("Problem loading Quartz!");
//            log.error("Problem loading Quartz!", schedEx);
//        }
//    }
//}
