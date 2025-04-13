<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Invoice Generator</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background: rgba(255, 255, 255, 0.15);
            padding: 40px;
            border-radius: 16px;
            backdrop-filter: blur(10px);
            -webkit-backdrop-filter: blur(10px);
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.25);
            width: 90%;
            max-width: 600px;
        }

        h1 {
            text-align: center;
            color: #ffffff;
            margin-bottom: 25px;
        }

        label {
            font-weight: bold;
            color: #fff;
        }

        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0 20px;
            border: none;
            border-radius: 10px;
            background-color: rgba(255, 255, 255, 0.2);
            color: #fff;
        }

        input::placeholder {
            color: #ddd;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background-color: #ffeb3b;
            color: #333;
            border: none;
            border-radius: 12px;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .btn:hover {
            background-color: #fdd835;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Invoice Generator</h1>
        <form action="InvoiceServlet" method="post">
            <label>Customer Name:</label>
            <input type="text" name="customerName" required>

            <label>Item 1:</label>
            <input type="text" name="itemName" placeholder="Item name" required>
            <input type="number" name="itemQty" placeholder="Quantity" required>
            <input type="number" name="itemPrice" placeholder="Price" step="0.01" required>

            <label>Item 2:</label>
            <input type="text" name="itemName" placeholder="Item name" required>
            <input type="number" name="itemQty" placeholder="Quantity" required>
            <input type="number" name="itemPrice" placeholder="Price" step="0.01" required>

            <label>Item 3:</label>
            <input type="text" name="itemName" placeholder="Item name">
            <input type="number" name="itemQty" placeholder="Quantity">
            <input type="number" name="itemPrice" placeholder="Price" step="0.01">

            <button type="submit" class="btn">Generate Invoice</button>
        </form>
    </div>
</body>
</html>
