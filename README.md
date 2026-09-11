# Location Library

Location library provides data types for locations of components.

## Publishing the Java artifact

Build the Maven artifact and its POM:

```shell
bazel build //java/location:location_export //java/location:location_export-pom
```

Ordinary builds use version `0.0.0-SNAPSHOT`. Supply a release version with a
Bazel definition:

```shell
bazel build --define=maven_version=1.0.3 \
  //java/location:location_export \
  //java/location:location_export-pom
```

Publish to a local Maven repository for testing:

```shell
MAVEN_REPO="file://${HOME}/.m2/repository" \
  bazel run --define=maven_version=1.0.3 \
    //java/location:location_export.publish
```

For a remote Maven repository, set `MAVEN_REPO`, `MAVEN_USER`, and
`MAVEN_PASSWORD` in the environment before running the same publish target.

### Publishing from GitHub Actions

The `Publish Maven artifact` workflow publishes an existing `vMAJOR.MINOR.PATCH`
tag when started manually from the Actions page. It passes the version from the
tag to Bazel when building and publishing the artifact. After Bazel uploads the
artifacts, the workflow transfers the staged repository to the Central Publisher
Portal as a user-managed deployment. An authorized person must then review it in
the Portal and either publish it to Maven Central or drop it.
