<h1>一个实现了登陆、注册、更新用户信息的基本功能的Android Demo</h1>
<p>Android前端：按<strong>MVP模式</strong>进行编写，核心使用<strong>Retrofit + RxJava + OkHttp</strong>进行交互和多线程</p>
<p>后台：使用<strong>SpringBoot + MyBatis</strong>实现User的RESTful API
<br/>
<h2>登陆界面</h2>

![alt text](https://github.com/zhuyst/Retrofit_Learn/blob/master/Screenshots/login.jpg)

<p>实现了<strong>记住密码和自动登陆</strong>功能，有Empty验证，当用户名/密码错误时，登陆按钮会变为红色并显示错误信息</p>
<br/>
<h2>注册界面</h2>

![alt text](https://github.com/zhuyst/Retrofit_Learn/blob/master/Screenshots/register.jpg)

<p>实现了基本的<strong>注册</strong>功能，用户名/密码有长度限制判断，重复密码有afterTextChange的事件不断与上面的密码进行比对，电子邮箱有使用Regex进行格式判断</p>
<br/>
<h2>用户信息界面</h2>

![alt text](https://github.com/zhuyst/Retrofit_Learn/blob/master/Screenshots/userinfo.jpg)

<p>实现了基本的<strong>用户信息查看与修改</strong>功能，进入界面先将个人信息请求并渲染到UI上，并提供修改信息的功能</p>
