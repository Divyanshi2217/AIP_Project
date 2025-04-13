package servlets;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class InvoiceServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/invoice_db";
    private static final String JDBC_USER = "root"; // change if needed
    private static final String JDBC_PASS = "@Devu";     // your MySQL password

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customer = request.getParameter("customerName");
        String[] names = request.getParameterValues("itemName");
        String[] qtys = request.getParameterValues("itemQty");
        String[] prices = request.getParameterValues("itemPrice");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        double grandTotal = 0;

        try {
            // Connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            String sql = "INSERT INTO invoices (customer_name, item_name, quantity, price, total) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (int i = 0; i < names.length; i++) {
                if (names[i] == null || names[i].trim().isEmpty()) continue;

                int qty = Integer.parseInt(qtys[i]);
                double price = Double.parseDouble(prices[i]);
                double total = qty * price;
                grandTotal += total;

                // Store in DB
                stmt.setString(1, customer);
                stmt.setString(2, names[i]);
                stmt.setInt(3, qty);
                stmt.setDouble(4, price);
                stmt.setDouble(5, total);
                stmt.executeUpdate();
            }

            conn.close();
        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Database Error: " + e.getMessage() + "</h3>");
            return;
        }

        // Display invoice as before
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Invoice</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', sans-serif; background: linear-gradient(135deg, #667eea, #764ba2); margin: 0; padding: 40px; color: #fff; }");
        out.println(".invoice-container { max-width: 900px; margin: auto; background: rgba(255,255,255,0.1); padding: 30px; border-radius: 20px; backdrop-filter: blur(12px); box-shadow: 0 8px 32px rgba(0,0,0,0.25); }");
        out.println("h2 { text-align: center; color: #ffeb3b; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        out.println("th, td { padding: 12px 15px; border-bottom: 1px solid rgba(255,255,255,0.3); color: white; text-align: left; }");
        out.println("th { background-color: rgba(255, 255, 255, 0.2); }");
        out.println("tfoot td { font-weight: bold; background-color: rgba(255, 255, 255, 0.1); }");
        out.println(".back-button { text-align: center; margin-top: 30px; }");
        out.println(".back-button a { text-decoration: none; padding: 12px 24px; background-color: #ffeb3b; color: #333; border-radius: 10px; font-weight: bold; }");
        out.println(".back-button a:hover { background-color: #fdd835; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='invoice-container'>");
        out.println("<h2>Invoice for " + customer + "</h2>");
        out.println("<table>");
        out.println("<tr><th>Item</th><th>Quantity</th><th>Price</th><th>Total</th></tr>");

        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].trim().isEmpty()) continue;

            int qty = Integer.parseInt(qtys[i]);
            double price = Double.parseDouble(prices[i]);
            double total = qty * price;

            out.println("<tr>");
            out.println("<td>" + names[i] + "</td>");
            out.println("<td>" + qty + "</td>");
            out.println("<td>₹" + String.format("%.2f", price) + "</td>");
            out.println("<td>₹" + String.format("%.2f", total) + "</td>");
            out.println("</tr>");
        }

        out.println("<tfoot><tr><td colspan='3'>Grand Total</td><td>₹" + String.format("%.2f", grandTotal) + "</td></tr></tfoot>");
        out.println("</table>");
        out.println("<div class='back-button'><a href='index.jsp'>← Back to Form</a></div>");
        out.println("</div></body></html>");
    }
}
