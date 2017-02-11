/*存放注意交互逻辑代码,要做到 JavaScript 模块化*/
var seckill = {
    // 封装秒杀相关 ajax 的 URL
    URL:{
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    // 处理秒杀逻辑
    handerSeckillKill: function (seckillId, node) {
        // 获取秒杀地址,控制显示逻辑,点击秒杀按钮执行秒杀
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');// 秒杀按钮
        // 发送 POST 请求
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            // 在回调函数中,执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                // 判断是否开启秒杀
                if (exposer['exposed']) {
                    // 开启秒杀
                    // 获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log('killUrl:'+killUrl);
                    // 给按钮绑定一次秒杀事件
                    // 使用 one 而不使用 click 的原因是 click 一直在绑定,只要我们不取消就一直在绑定,one 只绑定一次.
                    $('#killBtn').one('click', function () {
                        // 执行秒杀请求操作
                        // 1:先禁用按钮
                        $(this).addClass('disabled');
                        // 2:发送秒杀请求执行请求
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                // 3:显示秒杀结果
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    // 未开启秒杀
                    // TODO
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    // 重新走一遍计时逻辑
                    seckill.countDown(seckillId, now, start, end);
                }
            } else {
                console.log('result=' + result);
            }
        });
    },
    // 验证手机号
    validatePhone: function (phone) {
      if (phone && phone.length == 11 && !isNaN(phone)) {
          return true;
      } else {
          return false;
      }
    },
    //
    countDown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        // 时间判断
        if (nowTime > endTime) {
            // 秒杀结束
            seckillBox.html('秒杀结束！');
        } else if (nowTime < startTime) {
            // 秒杀未开始, 计时事件绑定
            // 加上一秒是为了防止时间偏移
            var killTime = new Date(startTime + 1000);
            seckillBox.countDown(killTime, function (event) {
                // 控制时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                // 时间倒计时完成后的回调事件
            }).on('finish.countdown', function () {
                // 获取秒杀地址,控制显示逻辑,点击秒杀按钮执行秒杀
                seckill.handerSeckillKill(seckillId, seckillBox);
            });
        } else {
            // 秒杀开始
            seckill.handerSeckillKill(seckillId, seckillBox);
        }
    },
    // 详情页秒杀逻辑
    detail:{
        // 详情页初始化
        init: function (params) {
            // 手机验证和登录, 计时交互
            // 在 cookie 中查找手机号
            var killPhone = $.cookie('killPhone');
            // 验证手机号, 如果为 false 的话则绑定手机号
            if (!seckill.validatePhone(killPhone)) {
                // 绑定 phone
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    // 电视弹出层
                    show : true,
                    // 禁止位置关闭
                    backdrop:'static',
                    // 关闭键盘事件
                    keyboard: false
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();

                    console.log('inputPhone' + inputPhone);//TODO

                    // 再次验证,如果验证通过则刷新页面
                    if (seckill.validatePhone(inputPhone)) {
                        // 电话写入 cookie
                        $.cookie('killPhone', inputPhone, {expires:7, path:'/seckill'});
                        // 刷新页面
                        window.location.reload()
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
            }
            // 已经登录, 进入计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    // 时间判断
                    seckill.countDown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result=' + result);
                }
            });
        }
    }
}

