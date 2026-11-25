provider "aws" {
  region  = var.aws_region
  profile = "enterprise-new"

  default_tags {
    tags = {
      Project     = var.project_name
      Environment = var.environment
      ManagedBy   = "Terraform"
    }
  }
}