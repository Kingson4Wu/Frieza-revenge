<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="java.io.*, java.net.*"%>
<html>
<body>
<h2><%
String fileName = "/WEB-INF/classes/git.properties";
InputStream ins = application.getResourceAsStream(fileName);
try {
	if (ins == null) {
		response.setStatus(response.SC_NOT_FOUND);
	} else {
		BufferedReader br = new BufferedReader((new InputStreamReader(ins)));
		String data;
		while((data= br.readLine())!= null)
		{
			out.println(data+"<br>");
		}
	}
}catch(IOException e) {
	out.println(e.getMessage());
}
%></h2>
</body>
</html>

<!--jsp中写java代码读取文件 -->