package Assignment_3;

public class FamilyTreeModel {

  private int NodeId;
  private String NodeName, AdditionalInfo;

  // getter methods
  public int getNodeId() {
    return NodeId;
  }

  public String getNodeName() {
    return NodeName;
  }

  public String getAdditionalInfo() {
    return AdditionalInfo;
  }

  // setter methods
  public void setNodeId(final int nodeId) {
    NodeId = nodeId;
  }

  public void setNodeName(final String nodeName) {
    NodeName = nodeName;
  }

  public void setAdditionalInfo(final String additionalInfo) {
    AdditionalInfo = additionalInfo;
  }
}
