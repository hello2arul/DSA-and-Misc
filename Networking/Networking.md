# REST vs RPC vs GraphQL

## REST (Representational State Transfer)

### Definition
REST is an architectural style that uses standard HTTP methods to perform CRUD (Create, Read, Update, Delete) operations on resources identified by URIs.

### Characteristics
- **Resource-Based**: Each resource has a unique URL (e.g., `/users/1`).
- **Stateless**: Each request from a client contains all the information needed to process the request.
- **Use of HTTP Methods**:
  - **GET**: Retrieve data
  - **POST**: Create new resources
  - **PUT/PATCH**: Update existing resources
  - **DELETE**: Remove resources
- **Data Formats**: Typically JSON or XML.

### Pros
- Simple and intuitive.
- Well-defined structure with a focus on resources.
- Caches easily due to statelessness.

### Cons
- Over-fetching and under-fetching data can occur.
- Limited flexibility in querying data.

---

## RPC (Remote Procedure Call)

### Definition
RPC allows clients to execute procedures (or functions) on a remote server as if they were local calls, using specific endpoints for each procedure.

### Characteristics
- **Action-Oriented**: Focuses on invoking operations rather than manipulating resources.
- **Methods and Parameters**: Each method is invoked with specific parameters (e.g., `/getUser?id=1`).

### Pros
- Simplicity in calling functions directly.
- Can lead to efficient communication for specific tasks.

### Cons
- Tightly coupled between client and server.
- Not as flexible for evolving APIs as REST or GraphQL.

---

## GraphQL

### Definition
GraphQL is a query language for APIs that allows clients to request exactly the data they need, potentially in a single request.

### Characteristics
- **Single Endpoint**: All requests go to a single endpoint (e.g., `/graphql`).
- **Flexible Queries**: Clients specify their data requirements in the query, enabling precise data retrieval.

### Pros
- Avoids over-fetching and under-fetching of data.
- Strongly typed schema with introspection capabilities.
- Easily extensible and adaptable to client needs.

### Cons
- More complex setup and learning curve.
- Potentially larger and more complex queries can lead to performance issues.

---

## Comparison Table

| Feature           | REST             | RPC               | GraphQL          |
|-------------------|------------------|-------------------|------------------|
| **Architecture**  | Resource-based    | Action-based       | Query-based       |
| **Endpoint**       | Multiple per resource | Multiple per action | Single endpoint    |
| **Data Fetching**  | Fixed response structure | Fixed response structure | Flexible response structure |
| **Caching**        | Easy to cache      | Difficult to cache | More complex caching |
| **Versioning**     | Often needs versioning | Less need for versioning | Evolving schema via types |
| **Complexity**     | Low complexity      | Medium complexity   | High complexity     |

---

## When to Use Each

### When to Use REST
- Simple CRUD applications.
- When resource representation is important.
- When using existing HTTP caching mechanisms.

### When to Use RPC
- Simple, straightforward tasks requiring direct function calls.
- High-performance scenarios where speed is critical.

### When to Use GraphQL
- Complex applications with varied data requirements.
- When clients need flexibility in their data queries.
- When the frontend needs to aggregate multiple resources in a single request.




# Overview of Networking Protocols

## UDP (User Datagram Protocol)

- **Type**: Connectionless
- **Characteristics**:
  - **No Connection Establishment**: Sends packets without establishing a connection.
  - **Unreliable**: No guarantees on delivery, ordering, or error checking.
  - **Low Latency**: Faster than TCP because of its lightweight nature.
- **Use Cases**:
  - Real-time applications (e.g., VoIP, online gaming, video streaming).
  - DNS queries.

---

## Other Networking Protocols

### TCP (Transmission Control Protocol)

- **Type**: Connection-oriented
- **Characteristics**:
  - **Reliable**: Ensures delivery of packets and maintains order.
  - **Connection Establishment**: Uses a three-way handshake to establish a connection.
  - **Flow Control**: Manages data transmission rate.
- **Use Cases**:
  - Web browsing (HTTP/HTTPS), email (SMTP, IMAP), file transfers (FTP).

### ICMP (Internet Control Message Protocol)

- **Type**: Network layer protocol
- **Characteristics**:
  - Used for diagnostic and error reporting.
  - Messages include destination unreachable, echo request/reply (ping).
- **Use Cases**:
  - Network troubleshooting (e.g., pinging hosts).

### HTTP/HTTPS (Hypertext Transfer Protocol/Secure)

- **Type**: Application layer protocol
- **Characteristics**:
  - **HTTP**: Stateless protocol for transmitting web content.
  - **HTTPS**: Secure version using SSL/TLS for encryption.
- **Use Cases**:
  - Web browsing, API communication.

### FTP (File Transfer Protocol)

- **Type**: Application layer protocol
- **Characteristics**:
  - Allows file transfer between client and server.
  - Can operate in active or passive mode.
- **Use Cases**:
  - Uploading/downloading files to/from servers.

### SMTP (Simple Mail Transfer Protocol)

- **Type**: Application layer protocol
- **Characteristics**:
  - Used for sending emails.
  - Works with POP3/IMAP for retrieving emails.
- **Use Cases**:
  - Sending email messages.

### SNMP (Simple Network Management Protocol)

- **Type**: Application layer protocol
- **Characteristics**:
  - Used for network management and monitoring.
  - Enables monitoring network devices.
- **Use Cases**:
  - Managing routers, switches, servers.

### ARP (Address Resolution Protocol)

- **Type**: Network layer protocol
- **Characteristics**:
  - Resolves IP addresses to MAC addresses in a local network.
- **Use Cases**:
  - Necessary for local network communication.

---

## Summary Table of Key Protocols

| Protocol   | Type        | Connection | Reliability | Common Use Cases              |
|------------|-------------|------------|-------------|--------------------------------|
| UDP        | Transport   | No         | Unreliable   | VoIP, gaming, streaming        |
| TCP        | Transport   | Yes        | Reliable      | Web, email, file transfer      |
| ICMP       | Network     | No         | N/A          | Diagnostics (ping)           |
| HTTP/HTTPS | Application | No         | N/A          | Web browsing                   |
| FTP        | Application | No         | N/A          | File transfers                 |
| SMTP       | Application | No         | N/A          | Email sending                  |
| SNMP       | Application | No         | N/A          | Network management             |
| ARP        | Network     | No         | N/A          | Address resolution             |

---

## Conclusion

Each protocol serves a distinct purpose in network communication, balancing trade-offs between reliability, speed, and complexity based on the application's needs. If you have specific questions about any protocol or need more details, let me know!



## URI vs. URL vs. URN

### URI (Uniform Resource Identifier)
- **Definition:** A URI is a string that uniquely identifies a resource, either by location, name, or both.
- **Purpose:** General term for any identifier of a resource.
- **Example:** `https://example.com/path/to/resource`, `urn:isbn:0451450523`

### URL (Uniform Resource Locator)
- **Definition:** A URL is a type of URI that specifies how to locate a resource by describing its access mechanism and location.
- **Purpose:** Used to access resources on the web or network.
- **Structure:** Includes scheme, host, and path.
- **Example:** `https://www.example.com/index.html`, `ftp://ftp.example.com/file.zip`

### URN (Uniform Resource Name)
- **Definition:** A URN is a type of URI that provides a unique and persistent name for a resource within a specific namespace, without specifying how to locate it.
- **Purpose:** Used for persistent, location-independent identification.
- **Example:** `urn:ietf:rfc:2141`, `urn:nbn:de:101-20120216`
