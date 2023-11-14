<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        #popup-trigger {
            display: none;
        }

        #popup-label {
            cursor: pointer;
        }

        #popup-container {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 60%;
            height: 60%;
            overflow: auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            resize: both;
            overflow: hidden;
        }

        #popup-trigger:checked + #popup-label + #popup-container {
            display: block;
        }

        #close-btn {
            position: absolute;
            top: 10px;
            right: 10px;
            cursor: pointer;
            font-size: 18px;
            color: #888;
        }

        #close-btn:hover {
            color: #555;
        }
    </style>

</head>

<script>
    function closePopup() {
        document.getElementById('popup-trigger').checked = false;
    }
</script>

</html>