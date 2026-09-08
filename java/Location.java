package co.atoms.lib.net.location;

/** Location for component observability. Can be used for debugging. */
public record Location(String region, String node) {
  public static final Location LOCAL = fromEnv();
  public static final Location UNKNOWN = new Location("", "");

  public static Location create(String region, String node) {
    return new Location(region, node);
  }

  private static Location fromEnv() {
    String region = System.getenv("APP_REGION");
    if (region == null || region.isBlank()) {
      region = "global";
    }
    String pod = System.getenv("HOSTNAME");
    if (pod == null || pod.isBlank()) {
      pod = "localhost";
    }
    return create(region, pod);
  }

  public static Location fromProto(co.atoms.lib.net.location.proto.Location location) {
    return create(location.getRegion(), location.getNode());
  }

  public co.atoms.lib.net.location.proto.Location toProto() {
    return co.atoms.lib.net.location.proto.Location.newBuilder()
        .setRegion(region)
        .setNode(node)
        .build();
  }

  @Override
  public String toString() {
    return String.format("%s/%s", region, node);
  }
}
