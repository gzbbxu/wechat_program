//app.js
App({
  server:{
    host:"http://127.0.0.1:8080",
    openId:"",
    nickName:"",
    avatarUrl:"",
    sessionkey:"",


  },
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    this.login();
    
    
  },
  login(){
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        console.log("登录后的code:"+res.code);
        var that = this;
        wx.request({

          url: this.server.host + "/aimeikongjian/Login",
          data: {
            code: res.code
          }, success: function (resp) {

            var thirdSessionId = resp.data;
            that.server.sessionkey = thirdSessionId;
            console.log("登录成功:" + thirdSessionId);
            wx.setStorageSync('thirdSessionId', thirdSessionId)
            that.getUserInfo();
          },fail:function(resp){
            console.log("error..登录");
          }
        })
      }
    })
  },
  getsetting(){
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
              //  this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  getUserInfo(){
    wx.getUserInfo({
      success:res=>{
        console.log("密钥:"+res.encryptedData);
        var that  = this;
        wx.request({
          url: this.server.host + "/aimeikongjian/DecodeUser",
           data:{
             encryptedData: res.encryptedData,
             iv: res.iv,
             sessionId: wx.getStorageSync('thirdSessionId')
           },success:function(resp){
              console.log("解密成功:"+resp.data);
              // this.server.oppenid = resp.data.openid;
              // this.server.nickName = resp.data.nickName;
              // this.server.avatarUrl = resp.data.navatarUrl;
              that.server.openId = resp.data.openId;
              that.server.nickName = resp.data.nickName;
              that.server.avatarUrl = resp.data.avatarUrl;
              if (that.userInfoReadyCallback) {
                console.log("开始回调");
                that.userInfoReadyCallback(res)
              }
            
           },fail:function(error){
             console.log("解密失败:" + error);
           }
        })
      }
    })
  },
  globalData: {
    userInfo: null
  }
})