# bank-fds

## Modules
- Kotlin 1.4

- Spring Boot 2.4.2
    - WebFlux
    - Spring Data Redis Reactive
    - Spring Data Mongo Reactive
    - Spring Reactive Kafka (*todo)
    - Spring Batch
  
- Data
    - Redis
    - Mongo

- Etc
    - Resilience4j
    - Feign Reactor
  
## Package Layout
- `internal-api`: API 제공을 프레젠테이션, 비즈니스 레이어 모듈

- `data`
  - `common`: 공통 데이터 및 설정 모듈
  - `bank-data-mongo`: 몽고 데이터 모듈
  - `bank-data-redis`: 레디스 데이터 모듈
  
- `core`
  - `common`: 공통 설정 모듈
  - `common-api`: 공통 API 인터페이스 모듈
  
- `client`
  - `common`: 공통 외부 통신 모듈 (feign)
  - `unknown1-client`: 클라이언트 모듈1
  - `unknown2-client`: 클라이언트 모듈2

## Feature
### circuit breaker
- resilience4j 설정으로 실패율, 지연율을 설정에 따라 계산하여 써킷을 관리
  - [*설정: ResilienceConfiguration]()
  - [*알림 설정: CustomResilienceRegistryEventConsumer]()
- Default Resilience 설정이 있으며 MSA 시스템, 인프라에 따라 구분해 놓음

