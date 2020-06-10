<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SocialNetwork</title>
    <link rel="stylesheet" href="/static/style.css"/>
    <style>
        body {
            background: url("/static/bg-03.jpg");
            background-size: 100%;
        }

        .footer {
            top: 82px;
            bottom: 0px;
            background: #FEDFC0;
            width: 100%;
            border-top: 2px solid #7B5427;
            position: absolute;
        }

        .center {
            position:absolute;
            width:100%;
            top:30%;
            text-align:center;
        }

        h1 {
            display: inline-block;
            padding: 6px;
            right: auto;
        }

        h2 {
            font-family: 'Times New Roman', Times, serif;
            font-size: 850%;
        }
    </style>
</head>
<body>
<div class="header">
    <h1>
        <a href="/login">Login</a>
    </h1>
    <h1>
        <a href="/registration">Registration</a>
    </h1>
</div>
<div class="footer">

    <div class="center">
        <h2>This is a Social Network</h2>
    </div>
    <br>

</div>
</body>
</html>
