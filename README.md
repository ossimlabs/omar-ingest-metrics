# omar-ingest-metrics

[![Build Status](https://jenkins.ossim.io/buildStatus/icon?job=omar-ingest-metrics-dev)]("#")

### Required environment variable
- OMAR_COMMON_PROPERTIES

### Optional environment variables
Only required for Jenkins pipelines or if you are running Nexus and/or Openshift locally

- OPENSHIFT_USERNAME
- OPENSHIFT_PASSWORD
- REPOSITORY_MANAGER_USER
- REPOSITORY_MANAGER_PASSWORD

## How to Build/Install omar-ingest-metrics-app locally

1. Git clone the following repos or git pull the latest versions if you already have them.
```
  git clone https://github.com/ossimlabs/omar-common.git
  git clone https://github.com/ossimlabs/omar-core.git
  git clone https://github.com/ossimlabs/omar-hibernate-spatial.git
  git clone https://github.com/ossimlabs/omar-ingest-metrics.git
```

2. Set OMAR_COMMON_PROPERTIES environment variable to the omar-common-properties.gradle (it is part of the omar-common repo).

3. Install omar-core-plugin (it is part of the omar-core repo).
```
 cd omar-core/plugins/omar-core-plugin
 gradle clean install
```

4. Install omar-hibernate-spatial-plugin
```
 cd omar-ingest-metrics/plugins/omar-hibernate-spatial-plugin
 gradle clean install
```

5. Install omar-ingest-metrics-plugin
```
 cd omar-ingest-metrics/plugins/omar-ingest-metrics-plugin
 gradle clean install
```

6. Build/Install omar-ingest-metrics-app
#### Build:
```
 cd omar-ingest-metrics/apps/omar-ingest-metrics-app
 gradle clean build
 ```
#### Install:
```
 cd omar-ingest-metrics/apps/omar-ingest-metrics-app
 gradle clean install
```
