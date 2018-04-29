package com.ecar.ecarnetwork.util.rx;


import com.ecar.ecarnetwork.interfaces.view.ILoading;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;

public class RxUtils {

    /********************************线程调度相关**************************************/
    /**
     * 是否加载等待 效果
     *
     * @param isLoading
     * @param loading
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> getScheduler(boolean isLoading, final ILoading loading) {
        Observable.Transformer<Object, Object> transformer = null;
        if (isLoading) {
            transformer = rxSchedulerLoading(loading);
        } else {
            transformer = rxScheduler();
        }
        return (Observable.Transformer<T, T>) transformer;
    }

    /**
     * 1.订阅在子线程、观察和取消订阅在UI线程.
     * 2.事件序列触发 显示loading 、结束（onError、onCompleted）取消Loading
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerLoading(final ILoading loading) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                if (loading != null) {
                                    loading.showLoading();
//                                    TagUtil.showLogError("loading show");
                                }
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(new Action0() {
                            @Override
                            public void call() {
                                if (loading != null) {
                                    loading.dismissLoading();
//                                    TagUtil.showLogError("loading dissmiss");
                                }
                            }
                        })
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 订阅在子线程、观察和取消订阅在UI线程
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxScheduler() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 自定义订阅线程
     * 观察和取消订阅在UI线程
     */
    public static <T> Observable.Transformer<T, T> rxScheduler(final Scheduler scheduler) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(scheduler)
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    /***************************end*****线程调度相关*****end*********************************/


    /********************************异常处理相关*******************************************/

    /**
     * 异步转同步
     */
    private void toSync(){
//        myObservable.toBlocking().first();
    }
    /**
     * 自定义处理 Rx 错误线程
     */
    public static void unifiedErrorHandler() {
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {

            }
        });
    }

    /***************************
     * end*****异常处理相关********end
     ******************************/

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    /********observable相关********************/
    /**
     * 返回一个被观察者，订阅后会传递onNext参数给订阅者
     *
     * @param onNext
     * @param <T>
     * @return
     */
    public static <T> Observable<T> justOnNext(final T onNext) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(onNext);
            }
        });
    }


    /*********************Rxjava 常用示例**********************/

    /**
     * Rx 链式 组成：Observable、Subscriber、事件、订阅
     * 1.创建 Observable 的方式：
     */
    public Observable createObservalbe() {
        String[] strs = new String[]{"1", "2", "3"};
        //方式1
        Observable<String> observable = Observable.from(strs);
        //方式2
        Observable<String[]> observable1 = Observable.just(strs);//更下面的  T的返回类型不同
        Observable<String> observable2 = Observable.just("1");
        //方式3 ，最原生
        Observable<Object> observable3 = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                //比如成功后 执行回调
                subscriber.onNext("内容1");
                subscriber.onNext("内容2");
                subscriber.onCompleted();
            }
        });
        return null;
    }


    /**
     * 异常处理：3种
     * 1.默认：onError
     * 异常后跳过中断 继续执行 ： 比如得到的数据跟预料的不对
     * 如何屏蔽异常而不把异常抛给onError，以下有两种选择：
     * 2. onErrorReturn()，在遇到错误时发射一个特定的数据
     * 3. onErrorResumeNext()，在遇到错误时发射一个数据序列
     */

    /**
     * 失败后重试：比如得到的数据跟预料的不对
     *Retry
     */


    /**
     * 点击后延时 不可点击
     */
    private void setDelayClick() {
//        RxView.clickEvents(button)
//                .throttleFirst(500, TimeUnit.MILLISECONDS)
//                .subscribe(clickAction);
        //区别于
        //        RxTextView.textChangeEvents(inputEditText)
//                .debounce(400, TimeUnit.MILLISECONDS)..observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<TextViewTextChangeEvent>()
    }

    /**
     * 线程切换：
     * 订阅线程只能在第一个subcribeOn 指定。
     * 观察线程可以有多个observeOn指定,指定接下来要观察的 最近的，要执行的 ，所在的线程
     */

    private void setSchedulers() {
        Observable observalbe = createObservalbe();//模拟得到一个被观察者
        observalbe.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .flatMap(new Func1<Object, Observable<String>>() {
                    @Override
                    public Observable<String> call(Object o) {
                        return Observable.just(o.toString());
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1() { //改变T 返回类型
                    @Override
                    public Object call(Object o) {
                        return o;//此处原样返回
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {

                    }
                });

    }

    /**
     * 使用combineLatest合并最近N个结点
     * 注册的时候所有输入信息（邮箱、密码、电话号码等）合法才点亮注册按钮。
     */


    /**
     * 使用merge合并两个数据源
     * 例如一组数据来自网络，一组数据来自文件，需要合并两组数据一起展示。
     */
    private void testMerge() {
//        Observable.merge(getDataFromFile(), getDataFromNet())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                               @Override
//                               public void onCompleted() {
//                                   log.d("done loading all data");
//                               }
//
//                               @Override
//                               public void onError(Throwable e) {
//                                   log.d("error");
//                               }
//
//                               @Override
//                               public void onNext(String data) {
//                                   log.d("all merged data will pass here one by one!")
//                               });
//                           }
    }

    /**
     * 使用concat和first做缓存
     * 依次检查memory、disk和network中是否存在数据，任何一步一旦发现数据后面的操作都不执行。
     */
    private void testCache() {
//        Observable<String> memory = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                if (memoryCache != null) {
//                    subscriber.onNext(memoryCache);
//                } else {
//                    subscriber.onCompleted();
//                }
//            }
//        });
//        Observable<String> disk = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                String cachePref = rxPreferences.getString("cache").get();
//                if (!TextUtils.isEmpty(cachePref)) {
//                    subscriber.onNext(cachePref);
//                } else {
//                    subscriber.onCompleted();
//                }
//            }
//        });
//
//        Observable<String> network = Observable.just("network");
//
////依次检查memory、disk、network
//        Observable.concat(memory, disk, network)
//                .first()
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(s -> {
//                    memoryCache = "memory";
//                    System.out.println("--------------subscribe: " + s);
//                });
    }

    /**
     * 使用timer做定时操作。当有“x秒后执行y操作”类似的需求的时候，想到使用timer
     * 例如：2秒后输出日志“hello world”，然后结束。
     */
    private void testTime() {
//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onCompleted() {
//                        log.d ("completed");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        log.e("error");
//                    }
//
//                    @Override
//                    public void onNext(Long number) {
//                        log.d ("hello world");
//                    }
//                });
    }

    /**
     * 使用interval做周期性操作。当有“每隔xx秒后执行yy操作”类似的需求的时候，想到使用interval
     * 例如：每隔2秒输出日志“helloworld”。
     */
    private void testinterval() {
//        Observable.interval(2, TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onCompleted() {
//                        log.d ("completed");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        log.e("error");
//                    }
//
//                    @Override
//                    public void onNext(Long number) {
//                        log.d ("hello world");
//                    }
//                });
    }

    /**
     * 使用schedulePeriodically做轮询请求
     */
    private void testPeriod() {
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(final Subscriber<? super String> observer) {
//
//                Schedulers.newThread().createWorker()
//                        .schedulePeriodically(new Action0() {
//                            @Override
//                            public void call() {
//                                observer.onNext(doNetworkCallAndGetStringResult());
//                            }
//                        }, INITIAL_DELAY, POLLING_INTERVAL, TimeUnit.MILLISECONDS);
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                log.d("polling….”));
//            }
//        })
    }

    /*****************************************组合操作************************/
    /**
     * 两个请求一起发，都收到后处理
     */
//    public void zipObservale(){
//        Observable.zip(
//                service.getUserPhoto(id),
//                service.getPhotoMetadata(id),
//                (photo, metadata) -> createPhotoWithData(photo, metadata))
//                .subscribe(photoWithData -> showPhoto(photoWithData));
//    }

    /**
     * 组合获取数据
     *
     * @return
     */
//    public Observable<List<OperationPositionWrapper>> getHome(final boolean isForceRefresh) {
//        final Observable<List<OperationPositionWrapper>> fromCache = Observable.create(
//                new Observable.OnSubscribe<List<OperationPosition>>() {
//                    @Override
//                    public void call(Subscriber<? super List<OperationPosition>> subscriber) {
//                        List<OperationPosition> dbCache = new Select().from(OperationPosition.class).queryList();
//                        if (dbCache != null) {
//                            subscriber.onNext(dbCache);
//                        }
//                        subscriber.onCompleted();
//                    }
//                })
//                .map(new Func1<List<OperationPosition>, List<OperationPositionWrapper>>() {
//                    @Override
//                    public List<OperationPositionWrapper> call(List<OperationPosition> operationPositions) {
//                        return OperationPositionMapper.wrap(operationPositions);
//                    }
//                })
//                .filter(new Func1<List<OperationPositionWrapper>, Boolean>() {
//                    @Override
//                    public Boolean call(List<OperationPositionWrapper> operationPositionWrappers) {
//                        return ListUtils.isNotEmpty(operationPositionWrappers);
//                    }
//                });
//
//        final Observable<List<OperationPositionWrapper>> fromNetwork = RepositoryUtils.observableWithApi(new GetOperationPositionsForYouleHomeApi())
//                .map(new Func1<List<OperationPositionPO>, List<OperationPositionWrapper>>() {
//                    @Override
//                    public List<OperationPositionWrapper> call(List<OperationPositionPO> operationPositionList) {
//                        return OperationPositionMapper.transform(operationPositionList);
//                    }
//                })
//                .doOnNext(new Action1<List<OperationPositionWrapper>>() {
//                    @Override
//                    public void call(List<OperationPositionWrapper> operationPositionWrappers) {
//                        if (ListUtils.isNotEmpty(operationPositionWrappers)) {
//                            new Delete().from(OperationPosition.class).queryClose();
//                        }
//                        for (OperationPositionWrapper wrapper : operationPositionWrappers) {
//                            wrapper.getOperationPosition().save();
//                        }
//                    }
//                });
//
//        if (isForceRefresh) {
//            return fromNetwork;
//        } else {
//            return Observable.concat(fromCache, fromNetwork);
//        }
//    }
    public static <T> Observable<List<T>> fromCollection(List<T> collection) {
        List<List<T>> collectionForRx = new ArrayList<>();
        collectionForRx.add(collection);
        return Observable.from(collectionForRx);
    }
}
//    public static <T> Observable<T> fromActor(final ActorRef actor){
//        final PublishSubject<T> subj = PublishSubject.create();
//
//        Observable<T> observable = Observable.create(new Observable.OnSubscribe<T>() {
//            @Override
//            public void call(final Subscriber<? super T> subscriber) {
//
//                /**
//                 * 创建和初始化subscribe的方法，修改
//                 * 缺省行为为代理请求转发，转发到订阅者的 'onNext'
//                 * 当有人提交到actor, 我们拦截这个actors 的响应RESPONSE
//                 * 然后类似管道一样导入到订阅者的工作队列.
//                 */
//                Subscribe msg = new Subscribe(new Action1<T>() {
//                    @Override
//                    public void call(T o) {
//                        subscriber.onNext(o);
//                    }
//                });
//
//                actor.tell(msg, ActorRef.noSender());
//            }
//        });
//
//        /**
//         * 创建一个有关这个Actor的订阅者，重新转发结果到
//         * 主题 subject (让其他订阅者订阅这个主题)
//         * 让akka observable不用担心管理谁是下一个消费者。
//         *
//         */
//        observable.subscribe(new Action1<T>() {
//            @Override
//            public void call(T o) {
//                subj.onNext(o);
//            }
//        });
//
//        /**
//         * Return the subject's observable stream for others to subscribe on
//         */
//        return subj.asObservable();
//    }



