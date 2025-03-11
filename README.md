# crypto-utils

A lightweight Java cryptography library that simplifies encryption and decryption using **AES/GCM/NoPadding**. Developed independently, it provides a straightforward API for secure cryptographic operations with minimal configuration.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Maven Dependency](#maven-dependency)
  - [Running Tests](#running-tests)
- [Contributing](#contributing)


## Features

- **AES/GCM/NoPadding Encryption:**  
  Provides both confidentiality and authenticity without requiring extra padding.
- **Secure Key Generation:**  
  Generates robust 256-bit AES keys for modern security standards.
- **Key Serialization:**  
  Easily converts keys to and from Base64 strings for seamless storage and transmission.
- **Simple and Intuitive API:**  
  Minimal setup required, making it easy to integrate secure encryption into any Java project.

## Prerequisites

- **Java 17** or higher
- **Maven 3.6.0** or later
- **Bouncy Castle** as the cryptographic provider (automatically configured)

## Getting Started

### Maven Dependency

Add the following dependency to your `pom.xml` to include crypto-utils in your project:

```xml
	<distributionManagement>
		<repository>
			<id>github</id>
			<name>GitHub Packages</name>
			<url>https://maven.pkg.github.com/Lewi217/crypto-utils</url>
		</repository>
	</distributionManagement>


	<dependencies>
		<dependency>
			<groupId>org.bouncycastle</groupId>
			<artifactId>bcprov-jdk18on</artifactId>
			<version>1.77</version>
		</dependency>

<dependency>
  <groupId>com.github.Lewi217</groupId>
  <artifactId>crypto-utils</artifactId>
  <version>1.0.1</version>
</dependency>

```
## Running Tests

To verify functionality, run the included JUnit tests with:

```xml
mvn test
```

## Contributing

Contributions are welcome! If you’d like to report bugs, request features, or submit pull requests, please:

    Fork the repository.
    Create a new branch for your changes.
    Commit your changes with clear messages.
    Open a pull request with a description of your modifications.



