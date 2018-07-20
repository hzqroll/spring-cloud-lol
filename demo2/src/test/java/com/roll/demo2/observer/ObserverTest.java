package com.roll.demo2.observer;

import rx.Observable;
import rx.Subscriber;

/**
 * @author haozq
 * Date: 2018/7/16 下午4:13
 */
public class ObserverTest {
	public static void main(String args[]) {
		Observable<String> observable = Observable.create(subscriber -> {
			subscriber.onNext("Hello RxJava");
			subscriber.onNext("I roll");
			subscriber.onCompleted();
		});

		//创建订阅者
		Subscriber<String> subscriber = new Subscriber<String>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onNext(String s) {
				System.out.println("Subscriber : " + s);
			}
		};

		observable.subscribe(subscriber);
	}
}
