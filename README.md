# MyExternalIP
Get external(public) IPv4 address programmatically

<h3><b>Usage:</b></h3>
<p><b>1.Add dependency in your app's build.gradle</b></p>
<p>compile(project(':myexternalip'))</p>
<p><b>2.Internet permission is necessary</b></p>
<p>uses-permission android:name="android.permission.INTERNET"</p>
<p><b>3.Must be run Non-UI thread</b></p>
<p>&nbsp&nbsp&nbsp&nbsp new Thread(new Runnable() {</p>
<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp @Override</p>
<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp public void run() {</p>
<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Log.d(TAG, "my external ip: " + MyExternalIp.getIp());</p>
<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Log.d(TAG, "my external ip: " + MyExternalIp.getIpFrom("http://whatismyip.org/"));</p>
<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Log.d(TAG, "my external ip: " + MyExternalIp.getCachedIp());</p>
<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp }</p>
<p>&nbsp&nbsp&nbsp&nbsp }).start();</p>
