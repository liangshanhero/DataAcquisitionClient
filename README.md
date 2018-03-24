#数据采集处理客户端
##Energy Device Manager Client

主类：cn.edu.scau.cmi.wuweijie.Bootstrap <br>

<a href="http://camel.apache.org">Camel框架</a> 实现 <br>
<a href="http://projects.spring.io/spring-batch/">Spring Batch</a>, 
<a href="http://www.quartz-scheduler.org">Quartz</a> 实现<br>

<hr>
<strong>注意</strong>: <br>
<ol>
	<li>FTP URI写法示例：ftp://192.168.42.1/目录?username=用户名&password=密码&passiveMode=true&delete=false&delay=5&ftpClient.controlEncoding=UTF-8&move=已上传文件转移目录 </li>
	<li>本地 URI写法示例: file:/Users/TESLA_CN/Documents/test/目录?delete=false&move=已上传文件转移目录 </li>
	<li>本地 URI写法示例(Windows，尚未测试): file:C:/Windows/System32?delete=false </li>
	<li><a href="http://camel.apache.org/file2.html">Camel File参数文档</a></li>
	<li><a href="http://camel.apache.org/ftp.html">Camel FTP参数文档</a></li>
</ol>
<hr>

Properties 配置文件参数说明: <br>
1.records.properties 文件为应用自动生成并配置，无需手动更改 <br>
2.client.properties 文件为运行必须参数，应用启动时会显示配置界面 <br>
部分参数介绍
<table>
	<tr>
		<td>参数名</td>
		<td>备注</td>
	</tr>
	<tr>
		<td>upload.from</td>
		<td>本地目录 或 FTP目录，该目录下的文件都会自动上传到 upload.to 参数所配置的地址 </td>
	</tr>
	<tr>
		<td>upload.to</td>
		<td>本地目录 或 FTP目录</td>
	</tr>
	<tr>
		<td>download.from</td>
		<td>本地目录 或 FTP目录，该目录下的文件都会自动下载到 download.to 参数所配置的地址</td>
	</tr>
	<tr>
		<td>download.to</td>
		<td>本地目录 或 FTP目录</td>
	</tr>
	<tr>
		<td>export.dir</td>
		<td>批处理 Excel 文件导出目录</td>
	</tr>
</table>

应用启动流程: <br>
1.使用 initContext.xml 初始化 Spring Context 并且显示参数配置界面 <br>
2.参数配置完成后，Spring 容器加载配置文件:
<ol>
	<li>applicationContext.xml 应用相关配置</li>
	<li>daoContext.xml 数据库相关配置</li>
	<li>batchContext.xml 批处理相关配置</li>
</ol>
3.创建新线程启动 Camel 进行目录、FTP相关操作，数据库导出相关操作定时调度 <br>

<hr>
下面是一些可能遇到的问题的解决方案: <br>
java.lang.NoSuchMethodError: org.hibernate.StatelessSession.createQuery(Ljava/lang/String;)Lorg/hibernate/Query; <br>
https://jira.spring.io/browse/SPR-14425

Camel 中文问题:添加参数 ftpClient.controlEncoding=UTF-8 (或gb等)
Camel ftp路径:用户登录后的路径成为相对根目录: <br>
	例如 pi 用户绝对路径为 /home/pi，则ftp地址后接的路径以 pi 为根目录。例如有文件夹绝对路径 /home/pi/test，则地址为 ftp://192.168.42.1/test。
	本地则直接写绝对路径，例如 file:/Users/TESLA_CN 或者 file:C:/Windows/System32