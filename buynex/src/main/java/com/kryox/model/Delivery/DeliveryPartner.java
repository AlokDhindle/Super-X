package com.kryox.model.Delivery;

public class DeliveryPartner {
    private String id;
    private String fullName;
    private String mobile;
    private String email;
    private String dob;
    private String gender;
    private String address;

    private String vehicleType;
    private String vehicleNumber;
    private String drivingLicense;

    private String profilePhotoPath;
    private String idCardPath;
    private String licenseDocPath;
    private String rcBookPath;

    private String accountHolder;
    private String bankName;
    private String accountNumber;
    private String ifscCode;

    private String emergencyContactName;
    private String emergencyContactPhone;
    private long createdAt;

    public DeliveryPartner() {
        // Required empty constructor for Firestore serialization
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getDrivingLicense() { return drivingLicense; }
    public void setDrivingLicense(String drivingLicense) { this.drivingLicense = drivingLicense; }

    public String getProfilePhotoPath() { return profilePhotoPath; }
    public void setProfilePhotoPath(String profilePhotoPath) { this.profilePhotoPath = profilePhotoPath; }

    public String getIdCardPath() { return idCardPath; }
    public void setIdCardPath(String idCardPath) { this.idCardPath = idCardPath; }

    public String getLicenseDocPath() { return licenseDocPath; }
    public void setLicenseDocPath(String licenseDocPath) { this.licenseDocPath = licenseDocPath; }

    public String getRcBookPath() { return rcBookPath; }
    public void setRcBookPath(String rcBookPath) { this.rcBookPath = rcBookPath; }

    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }

    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { this.emergencyContactPhone = emergencyContactPhone; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}