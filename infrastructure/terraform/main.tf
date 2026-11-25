locals {
  availability_zones = [
    "ap-south-1a",
    "ap-south-1b"
  ]

  cluster_name = "${var.project_name}-${var.environment}-eks"

  common_tags = {
    Project     = var.project_name
    Environment = var.environment
    ManagedBy   = "Terraform"
  }
}