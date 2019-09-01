package com.wfj.tos.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/async")
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @RequestMapping("/servlet/orig")
    public void todo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //这里来个休眠
        Thread.sleep(100);
        response.getWriter().println("这是【正常】的请求返回");
    }

    @RequestMapping("/servlet/async")
    public void todoAsync(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                logger.info("执行完成");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                logger.info("超时：{}", event);
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                logger.info("onError | ", event.getThrowable());
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                logger.info("线程开始");
            }
        });

        //设置超时时间
        asyncContext.setTimeout(200);

        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    logger.info("内部线程：" + Thread.currentThread().getName());
                    asyncContext.getResponse().setCharacterEncoding("utf-8");
                    asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                    asyncContext.getResponse().getWriter().println("这是【异步】的请求返回");
                } catch (Exception e) {
                    logger.error("异常：", e);
                }
                //异步请求完成通知
                //此时整个请求才完成
                //其实可以利用此特性 进行多条消息的推送 把连接挂起。。
                asyncContext.complete();
            }
        });

        //此时之类 request的线程连接已经释放了
        logger.info("线程：" + Thread.currentThread().getName());

    }

    @RequestMapping("/callable")
    public Callable<String> callable() {

        logger.info("外部线程：" + Thread.currentThread().getName());

        return new Callable<String>() {

            @Override

            public String call() throws Exception {

                logger.info("内部线程：" + Thread.currentThread().getName());

                return "callable!";

            }

        };

    }

    @RequestMapping("/webAsyncTask")
    public WebAsyncTask<String> webAsyncTask() {
        logger.info("外部线程：" + Thread.currentThread().getName());
        WebAsyncTask<String> result = new WebAsyncTask<String>(60 * 1000L, new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("内部线程：" + Thread.currentThread().getName());
                return "WebAsyncTask!!!";
            }
        });

        result.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                return "WebAsyncTask超时!!!";
            }
        });
        result.onCompletion(new Runnable() {
            @Override
            public void run() {
                //超时后 也会执行此方法
                logger.info("WebAsyncTask执行结束");
            }
        });
        return result;
    }
}
