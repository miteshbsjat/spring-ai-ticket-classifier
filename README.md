# AI Ticket Classifier (Spring AI + Ollama)

A Spring Boot microservice that leverages **Llama 3.1** via **Ollama** to automatically classify support tickets into structured categories and priority levels.

## 🚀 Features

* **Local LLM Integration**: Uses Ollama for privacy and cost-efficiency.
* **Structured Output**: Automatically maps LLM responses to a Java `Record`.
* **Smart Classification**: Detects `BILLING`, `TECHNICAL`, or `GENERAL` categories.
* **Intelligent Prioritization**: Assigns `HIGH`, `MEDIUM`, or `LOW` priority based on sentiment and urgency (e.g., money loss or service failure).

---

## 🏗️ Project Structure

```text
ticket-classifier/
├── src/main/java/com/example/ticket_classifier/
│   ├── controller/   # REST Endpoints
│   ├── service/      # LLM logic via ChatClient
│   └── model/        # Structured Records (Category & Priority Enums)
└── src/main/resources/
    └── application.yml # Ollama & Model config

```

---

## ⚙️ Configuration

The application is configured to talk to Ollama on the default port `11434`.

**application.yml**

```yaml
spring:
  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        options:
          model: llama3.1:latest
          temperature: 0.1

```

## 🛠️ Build & Compilation

Since this is a Gradle-based project, you use the Gradle Wrapper (`gradlew`) to handle compilation and packaging. This ensures everyone uses the same Gradle version without needing to install it manually.

* Full Build (Compile + Test + Package)

To run a full build cycle. This will generate an executable JAR file in `build/libs/`.

```bash
./gradlew clean build -x test
```

---


---
## 🏃 How to Run

1. **Start Ollama**: Ensure `ollama run llama3.1:latest` is working.
2. **Build & Run**:
```bash
./gradlew bootRun

```

---

## 🧪 API Usage & Examples

The service exposes a POST endpoint: `http://localhost:8080/classify`

### 1. High Priority (Urgent Technical/Billing)

**Request:**

```bash
curl -s -X POST http://localhost:8080/classify \
-H "Content-Type: application/json" \
-d '{"text": "URGENT: Cannot log in and my account shows zero balance!"}' | jq

```

**Response:**

```json
{
  "category": "BILLING",
  "priority": "HIGH",
  "reasoning": "User is angry and mentions money loss"
}

```

### 2. Standard Technical Issue

**Request:**

```bash
curl -s -X POST http://localhost:8080/classify \
-H "Content-Type: application/json" \
-d '{"text": "OTP is not received for initiating the transaction"}' | jq

```

**Response:**

```json
{
  "category": "TECHNICAL",
  "priority": "MEDIUM",
  "reasoning": "The issue is related to the transaction initiation process."
}

```

### 3. General Query (Low Priority)

**Request:**

```bash
curl -s -X POST http://localhost:8080/classify \
-H "Content-Type: application/json" \
-d '{"text": "C is better than C++"}' | jq

```

**Response:**

```json
{
  "category": "GENERAL",
  "priority": "LOW",
  "reasoning": "The statement is subjective and does not relate to a specific problem."
}

```
---