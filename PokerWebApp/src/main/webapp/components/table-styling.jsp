<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <style>

        .table {

            display: grid;
            grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
            grid-template-rows: 1fr 1fr 1fr 1fr 1fr 1fr;
            grid-auto-columns: 1fr;
            gap: 0px 0px;
            grid-template-areas:
                "table-area table-area table-area table-area logo-area"
                "table-area table-area table-area table-area button-area"
                "table-area table-area table-area table-area button-area"
                "table-area table-area table-area table-area button-area"
                "table-area table-area table-area table-area button-area";
        }

        .logo-area {

            grid-area: logo-area;
            background-color: #F1F1F1;
        }

        .button-area {

            padding: 20px;
            grid-area: button-area;
            background-color: #F1F1F1;
            justify-items: center;
            align-items: center;
        }

        .table-area {

            grid-area: table-area;
            background-color: #FFFFFF;
        }

        .blue-button {

            display: inline-block;
            padding: 20px 20px;
            font-size: 25px;
            text-align: center;
            text-decoration: none;
            background-color: #3498db;
            color: #ffffff;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            margin-bottom: 5px;
            margin-top: 5px;
        }

        .blue-button:hover {
            background-color: #2980b9;
        }

        .red-button {

            display: inline-block;
            padding: 20px 20px;
            font-size: 25px;
            text-align: center;
            text-decoration: none;
            background-color: #FF0000;
            color: #ffffff;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            margin-bottom: 5px;
            margin-top: 5px;
        }

        .red-button:hover {
            background-color: #D90000;
        }

        .rounded-rectangle {

            background-color: #FFFFFF;
            width: auto;
            height: auto;
            padding: 20px;
            border-radius: 20px;
            margin-bottom: 10px;
            margin-top: 10px;
        }

    </style>

</head>
<body>

</body>
</html>
