package com.shanhai.v2exnotify.util;

import java.util.concurrent.*;

public class ThreadPoolUtils {
	public static ThreadPoolExecutor pool = null;

	// 无响应执行
	public static void execute(Runnable runnable) {
		getThreadPool().execute(runnable);
	}

	// 有响应执行
	public static <T> Future<T> submit(Callable<T> callable) {
		return getThreadPool().submit(callable);
	}


	// 创造线程池
	private static synchronized ThreadPoolExecutor getThreadPool() {
		if (pool == null) {
			// 获取处理器数量
			int cpuNum = Runtime.getRuntime().availableProcessors();
			// 根据cpu数量,计算出合理的线程并发数
			// 最佳线程数目 = （（线程等待时间+线程CPU时间）/线程CPU时间 ）* CPU数目
			int maximumPoolSize = cpuNum * 2 + 1;
			// 七个参数
			// 1. 核心线程数
			// 2. 最大线程数
			// 3. 空闲线程最大存活时间
			// 4. 时间单位
			// 5. 阻塞队列
			// 6. 创建线程工厂
			// 7. 拒绝策略
			pool = new ThreadPoolExecutor(maximumPoolSize - 1,
					maximumPoolSize,
					5,
					TimeUnit.SECONDS,
					new ArrayBlockingQueue<>(50),
					Executors.defaultThreadFactory(),
					new ThreadPoolExecutor.AbortPolicy());
		}
		return pool;
	}
}
