import sqlite3

# Connect to SQLite database (or create one if it doesn't exist)
conn = sqlite3.connect("test.db")
cursor = conn.cursor()

# Create a sample users table (for demo purposes)
cursor.execute("DROP TABLE IF EXISTS users")  # Remove old table
cursor.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, username TEXT, password TEXT)")

# Insert a test user
cursor.execute("INSERT INTO users (username, password) VALUES ('admin', 'password123')")
conn.commit()

# Take user input (simulating login)
username = input("Enter username: ")
password = input("Enter password: ")

# 🚨 SQL Injection Vulnerability: Concatenating user input directly into the query
query = f"SELECT * FROM users WHERE username = '{username}' AND password = '{password}'"
print(f"Executing Query: {query}")  # Debugging output

cursor.execute(query)  # Unsafe execution
result = cursor.fetchone()

if result:
    print("Login successful! Welcome,", result[1])
else:
    print("Invalid username or password.")

conn.close()
