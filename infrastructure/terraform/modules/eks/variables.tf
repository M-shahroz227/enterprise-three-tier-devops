variable "cluster_name" {
  description = "EKS cluster name"
  type        = string
}

variable "kubernetes_version" {
  description = "Kubernetes version"
  type        = string
}

variable "subnet_ids" {
  description = "Subnet IDs for EKS"
  type        = list(string)
}

variable "node_instance_types" {
  description = "Worker node EC2 instance types"
  type        = list(string)
}

variable "node_desired_size" {
  description = "Desired worker count"
  type        = number
}

variable "node_min_size" {
  description = "Minimum worker count"
  type        = number
}

variable "node_max_size" {
  description = "Maximum worker count"
  type        = number
}

variable "node_disk_size" {
  description = "Worker disk size in GiB"
  type        = number
}

variable "cluster_endpoint_public_access_cidrs" {
  description = "Allowed public CIDRs for EKS API"
  type        = list(string)
}

variable "tags" {
  description = "Common resource tags"
  type        = map(string)
  default     = {}
}