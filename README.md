# GiveClock - Minecraft Bukkit Plugin

一个Minecraft Java版的Bukkit插件基础项目。

## 功能概述

- 命令系统（/giveclock）
- 玩家事件监听器
- 权限管理

## 项目结构

```
giveclock/
├── pom.xml                          # Maven 构建配置
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/giveclock/
│       │       ├── GiveClock.java           # 插件主类
│       │       ├── commands/
│       │       │   └── GiveClockCommand.java # 命令处理
│       │       └── listeners/
│       │           └── PlayerListener.java   # 事件监听
│       └── resources/
│           └── plugin.yml           # 插件配置文件
└── README.md                        # 项目说明
```

## 编译和构建

使用Maven编译项目：

```bash
mvn clean package
```

编译后的JAR文件将在 `target/` 目录中。

## 安装插件

1. 将生成的JAR文件复制到Bukkit服务器的 `plugins/` 文件夹
2. 重启服务器或使用 `/reload` 命令

## 开发指南

### 添加新命令

在 `com.example.giveclock.commands` 包中创建新的命令类，实现 `CommandExecutor` 接口，然后在 `GiveClock.java` 中注册。

### 添加事件监听

在 `com.example.giveclock.listeners` 包中创建新的监听器类，实现 `Listener` 接口，然后在 `GiveClock.java` 中注册。

### 权限

所有权限定义在 `plugin.yml` 中。

## 版本信息

- Bukkit版本：1.20.1
- Java版本：1.8+
- Maven版本：3.6+

## 许可证

MIT License
