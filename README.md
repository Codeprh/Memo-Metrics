# Memo-Metrics

# Introduction(简介)

> todo
>

# CNCF理论基础

## CNCF Landscape

![](https://tva1.sinaimg.cn/large/006tNbRwly1gaddn14q3aj31hc0u0nph.jpg)

## CNCF TrailMap

![](https://tva1.sinaimg.cn/large/006tNbRwly1gaddo2qgo0j30u00ysu10.jpg)

# 项目架构

## 项目组件

| 中间件      | 中间件介绍 | 产品 |
| ----------- | ---------- | ---- |
| 注册中心    |            |      |
| 网关        |            |      |
| 配置中心    |            |      |
| 调用链监控  |            |      |
| 服务调用    |            |      |
| 授权认证    |            |      |
| 容错限流    |            |      |
| Metries监控 |            |      |
| ELK         |            |      |
| docker+K8S  |            |      |



# 项目规划

## Meeting01

1. 实现语言层无关，cncf组件微服务治理方案。
2. 语言Java、go、X
3. 客户端：B端（暂定）
4. TodolistApplication背景下在[proof-of-concept application](https://piggymetrics.tk/)。
5. CAP：CP

# Service Mesh和Istio架构

serviceMesh(SideCar)或者Istio，解决服务发现和负载均衡方式，是模式三：主机独立进程代理的变种版本。

## Service Mesh(服务网格)

### SideCar

![image-20191229102823539](https://tva1.sinaimg.cn/large/006tNbRwly1gaddvkew32j30zg0gkdr5.jpg)

### 服务网格

![image-20191229102855794](https://tva1.sinaimg.cn/large/006tNbRwly1gaddw4n2yij31e10u07et.jpg)

## Istio架构

![image-20191229102941630](https://tva1.sinaimg.cn/large/006tNbRwly1gaddwwymfoj319s0om47n.jpg)

> Istio各个组件的介绍
>
> Istio is an open platform for providing a uniform way to integrate microservices, manage traffic flow across microservices, enforce policies and aggregate telemetry data. Istio's control plane provides an abstraction layer over the underlying cluster management platform, such as Kubernetes.
>
> Istio is composed of these components:
>
> - **Envoy** - Sidecar proxies per microservice to handle ingress/egress traffic between services in the cluster and from a service to external services. The proxies form a *secure microservice mesh* providing a rich set of functions like discovery, rich layer-7 routing, circuit breakers, policy enforcement and telemetry recording/reporting functions.
>
>   > Note: The service mesh is not an overlay network. It simplifies and enhances how microservices in an application talk to each other over the network provided by the underlying platform.
>
> - **Mixer** - Central component that is leveraged by the proxies and microservices to enforce policies such as authorization, rate limits, quotas, authentication, request tracing and telemetry collection.
>
> - **Pilot** - A component responsible for configuring the proxies at runtime.
>
> - **Citadel** - A centralized component responsible for certificate issuance and rotation.
>
> - **Citadel Agent** - A per-node component responsible for certificate issuance and rotation.
>
> - **Galley**- Central component for validating, ingesting, aggregating, transforming and distributing config within Istio.
>
> Istio currently supports Kubernetes and Consul-based environments. We plan support for additional platforms such as Cloud Foundry, and Mesos in the near future.



# ComponentsResearch(组件调研)

## 组件选型依据

todo

## 注册中心![image-20191229105634287](https://tva1.sinaimg.cn/large/006tNbRwly1gadeovrfrhj31v80s0tfj.jpg)



## 网关

### 网关-ServiceProxy

#### 谈谈Service Proxy组件选择的问题

微服务架构的根本问题：服务发现和负载均衡。解决方案：代理。

#### ServiceProxy演进过程

模式一：传统集中式代理

![image-20191229101509124](https://tva1.sinaimg.cn/large/006tNbRwly1gaddhs0g9zj30um0csgnk.jpg)

模式二：服务注册中心+客户端嵌入式处理

![image-20191229101558660](https://tva1.sinaimg.cn/large/006tNbRwly1gaddimvk15j30te0cc75n.jpg)

模式三：主机独立进程代理

![image-20191229101633458](https://tva1.sinaimg.cn/large/006tNbRwly1gaddj8tteoj30uy0fodhq.jpg)

#### Envoy

#### traefik

### 网关-APIGateway

#### Sentinel

#### ApiSix

#### Zuul

## 服务调用

### gRPC





# MeetingSummary(会议总结)

## 2019-12-29

![](https://tva1.sinaimg.cn/large/006tNbRwly1gadcnok3o1j31400u04qt.jpg)




