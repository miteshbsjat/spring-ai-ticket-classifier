# AI Ticket Classifier (Spring AI + Ollama)

A Spring Boot microservice that leverages **Llama 3.1** via **Ollama** to automatically classify support tickets into structured categories. It uses Spring AI's `ChatClient` with structured output conversion to ensure every response is a valid JSON object.

## 🚀 Features

* **Local LLM Integration**: Uses Ollama for privacy and cost-efficiency.
* **Structured Output**: Automatically maps LLM text to a Java `Record` (JSON).
* **Smart Classification**: Detects `BILLING`, `TECHNICAL`, and `GENERAL` issues based on natural language context.
* **Modern Stack**: Built with Spring Boot 3.4+, Spring AI (Milestone 5), and Gradle.

---

## 🛠️ Prerequisites

1. **Ollama Installed**: [Download Ollama](https://ollama.com/)
2. **Model Downloaded**:
```bash
ollama pull llama3.1:latest

```


3. **Java 17+**

---

## 🏗️ Project Structure

```text
ticket-classifier/
├── src/main/java/com/example/ticket_classifier/
│   ├── controller/   # REST Endpoints
│   ├── service/      # LLM logic via ChatClient
│   └── model/        # Structured Records (POJOs)
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
          temperature: 0.1 # Low temperature for consistent classification

```

---

## 🏃 Running the Application

Build and run the service using the Gradle wrapper:

```bash
./gradlew bootRun

```

---

## 🧪 API Usage & Examples

The service exposes a single POST endpoint: `http://localhost:8080/classify`

### 1. Billing Issue

```bash
curl -X POST http://localhost:8080/classify \
-H "Content-Type: application/json" \
-d '{"text": "Customer charged twice on credit card"}'

```

**Response:**

```json
{
  "category": "BILLING",
  "reasoning": "Duplicate charge on customer's credit card"
}

```

### 2. Technical Issue

```bash
curl -X POST http://localhost:8080/classify \
-H "Content-Type: application/json" \
-d '{"text": "OTP is not received for initiating the transaction"}'

```

**Response:**

```json
{
  "category": "TECHNICAL",
  "reasoning": "The issue is related to the transaction initiation process, which falls under technical support."
}

```

### 3. General Query

```bash
curl -X POST http://localhost:8080/classify \
-H "Content-Type: application/json" \
-d '{"text": "C is better than C++"}'

```

**Response:**

```json
{
  "category": "GENERAL",
  "reasoning": "The statement is subjective and does not relate to a specific issue or problem."
}

```

---

## 🔧 Troubleshooting

* **Dependency Issues**: Ensure the Spring Milestone repository is added to `build.gradle` as Spring AI is not yet on Maven Central.
* **Timeouts**: If the LLM takes too long to respond on the first request, increase the client timeout in `application.yml`.
