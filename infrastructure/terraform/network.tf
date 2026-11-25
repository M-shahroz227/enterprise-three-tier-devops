module "vpc" {
  source = "./modules/vpc"

  name                = "${var.project_name}-${var.environment}"
  vpc_cidr            = var.vpc_cidr
  availability_zones  = local.availability_zones
  public_subnet_cidrs = var.public_subnet_cidrs

  tags = local.common_tags
}