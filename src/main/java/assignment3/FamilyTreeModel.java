package assignment3;

public class FamilyTreeModel {

  private int nodeId;
  private String nodeName, additionalInfo;

  // getter methods
  public int getNodeId() {
    return nodeId;
  }

  public String getNodeName() {
    return nodeName;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  // setter methods
  public void setNodeId(final int nodeId) {
    this.nodeId = nodeId;
  }

  public void setNodeName(final String nodeName) {
    this.nodeName = nodeName;
  }

  public void setAdditionalInfo(final String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }
}
