# Lab 4 Yashica Sharma

## Business Domain: Insurance
The chosen business domain is the insurance industry, specifically focusing on insurance policies. This domain is vital as it involves managing policies, claims, and customers to ensure financial security. By working on this domain, we can explore various business processes like policy issuance, premium payments, and claim processing.

### Additional Entities:
- **Customer**: Represents individuals holding policies, linked to multiple policies.
- **Claim**: Represents insurance claims filed against a policy.
- **Payment**: Tracks premium payments associated with a policy.
- **Agent**: Represents an insurance agent managing policies for clients.

These entities relate through foreign key relationships and are crucial in the insurance ecosystem.


### JUnit Test Cases
InsurancePolicyJPATest.java: This class tests the CRUD operations on the InsurancePolicy entity using JPA:
- **Create**: Creates a test record ensuring the policy ID is generated.
- **Read**: Uses em.find() to fetch/read an entity(record).
- **Update**: Changes an attribute and verifies persistence.
- **Delete**: Removes an entity and verifies it's null.

### Output Screenshot:
![image](https://github.com/user-attachments/assets/7f639520-c39f-4916-afa5-c8560e7f7edb)

