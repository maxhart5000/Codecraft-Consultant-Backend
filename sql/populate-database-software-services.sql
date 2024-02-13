-- Schema codecraft_consulting

-- Drop existing schema if exists
DROP SCHEMA IF EXISTS `codecraft_consulting`;

-- Create new schema
CREATE SCHEMA `codecraft_consulting`;

-- Use the newly created schema
USE `codecraft_consulting`;

-- Drop tables if they exist
DROP TABLE IF EXISTS `product_category`;
DROP TABLE IF EXISTS `product`;

-- Table structure for table `product_category`
CREATE TABLE `product_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- Table structure for table `product`
CREATE TABLE `product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `sku` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `description` TEXT,
  `unit_price` DECIMAL(13,2) DEFAULT NULL,
  `image_url` VARCHAR(255) DEFAULT NULL,
  `active` BIT DEFAULT 1,
  `date_created` DATETIME(6) DEFAULT NULL,
  `last_updated` DATETIME(6) DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- Insert categories
INSERT INTO product_category(category_name) VALUES
('Java'),
('Spring Boot'),
('MySQL'),
('TypeScript'),
('Angular');

-- Insert Java products
INSERT INTO product (sku, name, description, unit_price, image_url, category_id, date_created) VALUES
('JAVA-1000', 'Advanced Java Development Toolkit', 'Empower your Java development projects with our comprehensive toolkit. From enterprise applications to web services, our toolkit offers a wide range of libraries, frameworks, and tools to streamline development and boost productivity.', 299.99, 'assets/images/products/java/java_toolkit.png', 1, NOW()),
('JAVA-1001', 'Java Enterprise Application Server', 'Deploy scalable and reliable enterprise applications with our Java application server. Built for performance and stability, our application server supports high-volume transactions and mission-critical workloads.', 399.99, 'assets/images/products/java/java_app_server.png', 1, NOW()),
('JAVA-1002', 'Secure Authentication Module for Java', 'Ensure the security of your Java applications with our authentication module. Featuring multi-factor authentication and encryption, our module protects sensitive data and prevents unauthorized access.', 199.99, 'assets/images/products/java/java_auth_module.png', 1, NOW()),
('JAVA-1003', 'Advanced Data Processing Library', 'Optimize data processing tasks with our Java library. From data analysis to real-time processing, our library offers high-performance algorithms and data structures to handle large datasets with ease.', 499.99, 'assets/images/products/java/java_data_library.png', 1, NOW()),
('JAVA-1004', 'Java Monitoring and Analytics Tool', 'Gain insights into your Java applications with our monitoring and analytics tool. Featuring real-time metrics and customizable dashboards, our tool helps you identify performance bottlenecks and optimize resource utilization.', 349.99, 'assets/images/products/java/java_monitoring_tool.png', 1, NOW());

-- Insert Spring Boot products
INSERT INTO product (sku, name, description, unit_price, image_url, category_id, date_created) VALUES
('SPRING-1000', 'Spring Boot Microservices Framework', 'Architect scalable microservices with our Spring Boot framework. Featuring cloud-native design principles and built-in support for Kubernetes, our framework accelerates development and deployment of microservices-based applications.', 299.99, 'assets/images/products/spring_boot/spring_microservices.png', 2, NOW()),
('SPRING-1001', 'Spring Boot Data Access Library', 'Simplify database access with our Spring Boot library. From CRUD operations to advanced queries, our library offers intuitive APIs and powerful abstractions to streamline data access in your applications.', 199.99, 'assets/images/products/spring_boot/spring_data_access.png', 2, NOW()),
('SPRING-1002', 'Spring Boot Security Module', 'Secure your Spring Boot applications with our security module. Featuring role-based access control and encryption, our module protects sensitive data and prevents common security vulnerabilities.', 349.99, 'assets/images/products/spring_boot/spring_security_module.png', 2, NOW()),
('SPRING-1003', 'Spring Boot Messaging Service', 'Facilitate communication between microservices with our messaging service. Built on top of Spring Boot and Apache Kafka, our service ensures reliable message delivery and low-latency communication.', 299.99, 'assets/images/products/spring_boot/spring_messaging_service.png', 2, NOW()),
('SPRING-1004', 'Spring Boot DevOps Toolkit', 'Automate deployment and monitoring of Spring Boot applications with our DevOps toolkit. Featuring CI/CD pipelines and container orchestration, our toolkit streamlines the DevOps lifecycle and improves collaboration between development and operations teams.', 399.99, 'assets/images/products/spring_boot/spring_devops_toolkit.png', 2, NOW());

-- Insert MySQL products
INSERT INTO product (sku, name, description, unit_price, image_url, category_id, date_created) VALUES
('MYSQL-1000', 'MySQL Database Cluster Solution', 'Deploy a highly available database cluster with our MySQL solution. Featuring automatic failover and data replication, our solution ensures data integrity and high availability for your applications.', 399.99, 'assets/images/products/mysql/mysql_cluster_solution.png', 3, NOW()),
('MYSQL-1001', 'MySQL Performance Tuning Toolkit', 'Optimize database performance with our MySQL toolkit. From query optimization to index tuning, our toolkit offers best practices and tools to maximize the performance of your MySQL databases.', 299.99, 'assets/images/products/mysql/mysql_performance_toolkit.png', 3, NOW()),
('MYSQL-1002', 'Secure Backup and Recovery System', 'Protect your MySQL databases with our backup and recovery system. Featuring incremental backups and point-in-time recovery, our system ensures data availability and disaster recovery readiness.', 249.99, 'assets/images/products/mysql/mysql_backup_system.png', 3, NOW()),
('MYSQL-1003', 'MySQL Replication Monitoring Tool', 'Monitor and manage MySQL replication with our monitoring tool. Featuring real-time alerts and performance metrics, our tool helps you identify replication issues and ensure data consistency across nodes.', 299.99, 'assets/images/products/mysql/mysql_replication_monitoring.png', 3, NOW()),
('MYSQL-1004', 'MySQL Security Compliance Suite', 'Ensure compliance with industry regulations and protect your data with our security suite. Featuring encryption and access controls, our suite helps you secure your MySQL databases and meet regulatory requirements.', 349.99, 'assets/images/products/mysql/mysql_security_suite.png', 3, NOW());

-- Insert TypeScript products
INSERT INTO product (sku, name, description, unit_price, image_url, category_id, date_created) VALUES
('TYPESCRIPT-1000', 'TypeScript Frontend Framework', 'Build modern and scalable web applications with our TypeScript framework. Featuring Angular and React integration, our framework offers a flexible and extensible architecture for frontend development.', 399.99, 'assets/images/products/typescript/typescript_frontend_framework.png', 4, NOW()),
('TYPESCRIPT-1001', 'TypeScript Node.js Development Toolkit', 'Streamline Node.js development with our TypeScript toolkit. From REST APIs to serverless functions, our toolkit provides essential libraries and tools to accelerate backend development with TypeScript.', 299.99, 'assets/images/products/typescript/typescript_nodejs_toolkit.png', 4, NOW()),
('TYPESCRIPT-1002', 'TypeScript Data Visualization Library', 'Visualize data and create interactive dashboards with our TypeScript library. Featuring D3.js and Chart.js integration, our library offers a wide range of customizable charts and graphs for data visualization.', 249.99, 'assets/images/products/typescript/typescript_data_visualization.png', 4, NOW()),
('TYPESCRIPT-1003', 'TypeScript Testing and Debugging Tools', 'Ensure code quality and reliability with our TypeScript testing and debugging tools. From unit tests to end-to-end testing, our tools offer comprehensive coverage and insights into your TypeScript code.', 349.99, 'assets/images/products/typescript/typescript_testing_debugging.png', 4, NOW()),
('TYPESCRIPT-1004', 'TypeScript Mobile App Development Framework', 'Build cross-platform mobile apps with our TypeScript framework. Featuring Ionic and React Native support, our framework enables rapid development and deployment of mobile applications.', 299.99, 'assets/images/products/typescript/typescript_mobile_framework.png', 4, NOW());

-- Insert Angular products
INSERT INTO product (sku, name, description, unit_price, image_url, category_id, date_created) VALUES
('ANGULAR-1000', 'Angular Material Design Components', 'Create stunning and responsive user interfaces with our Angular Material components. Featuring a wide range of UI components and theming options, our components offer a seamless and intuitive user experience.', 349.99, 'assets/images/products/angular/angular_material_components.png', 5, NOW()),
('ANGULAR-1001', 'Angular State Management Library', 'Manage application state effectively with our Angular state management library. Featuring NgRx and Redux integration, our library offers predictable state management and synchronization across components.', 299.99, 'assets/images/products/angular/angular_state_management.png', 5, NOW()),
('ANGULAR-1002', 'Angular Testing Utilities', 'Ensure code quality and reliability with our Angular testing utilities. Featuring Jasmine and Karma integration, our utilities offer tools and best practices for writing unit tests and end-to-end tests for Angular applications.', 249.99, 'assets/images/products/angular/angular_testing_utilities.png', 5, NOW()),
('ANGULAR-1003', 'Angular Server-Side Rendering Solution', 'Improve performance and SEO for your Angular applications with our server-side rendering solution. Featuring Angular Universal, our solution enables server-side rendering for faster initial page loads and better search engine visibility.', 399.99, 'assets/images/products/angular/angular_server_side_rendering.png', 5, NOW()),
('ANGULAR-1004', 'Angular Progressive Web App (PWA) Toolkit', 'Transform your Angular applications into progressive web apps with our PWA toolkit. Featuring Angular Service Worker and PWA manifest generation, our toolkit enables offline capabilities and improved user engagement.', 299.99, 'assets/images/products/angular/angular_pwa_toolkit.png', 5, NOW());
