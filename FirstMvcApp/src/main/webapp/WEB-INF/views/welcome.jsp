<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<style>
            .blink {
                animation: blinker 1.5s linear infinite;
                font-family: sans-serif;
            }
            @keyframes blinker {
                50% {
                    opacity: 0;
                }
            }
        </style>
</head>
<body>
	<font color='blue'><h1>
			<marquee width="" direction="left" height="100px" scrollamount="30" class='blink'>
				${message} </marquee>
		</h1></font>
</body>
</html>