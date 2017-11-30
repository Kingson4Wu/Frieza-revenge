
import java.io.Serializable;
import java.util.Date;

/** treatment_record
	ID    BIGINT(18)
	TREATMENT_RECORD_ID    VARCHAR(32)
	MEDICAL_SERVICE_ITEM_ID    VARCHAR(32)
	APPOINTMENT_ID    VARCHAR(32)
	PRODUCT_ID    VARCHAR(32)
	PRODUCT_PACKAGE_ID    VARCHAR(32)
	CLINIC_ID    VARCHAR(32)
	SHOP_ID    VARCHAR(32)
	PATIENT_ID    VARCHAR(32)
	TITLE    VARCHAR(64)
	PRODUCT_PACKAGE_TITLE    VARCHAR(64)
	DOCTOR_ID    VARCHAR(32)
	OUTPATIENT_RECORD_CREATE_TIME    DATETIME(19)
	SERVICE_TIMES    INT(11)
	EXECUTED_TIMES    INT(11)
	THERAPIST_USER_ID    VARCHAR(32)
	APPOINTMENT_TIME    DATETIME(19)
	IS_EXECUTED    TINYINT(3)
	IS_APPOINTMENTED    TINYINT(3)
	IS_AVAILABLE    TINYINT(3)
	CREATE_TIME    DATETIME(19)
	UPDATE_TIME    DATETIME(19)
	IS_PACKAGE    TINYINT(3)
	STATUS    TINYINT(3)
*/
public class Treatment_Record {
	private Long id;
	private String treatmentRecordId;
	private String medicalServiceItemId;
	private String appointmentId;
	private String productId;
	private String productPackageId;
	private String clinicId;
	private String shopId;
	private String patientId;
	private String title;
	private String productPackageTitle;
	private String doctorId;
	private Date outpatientRecordCreateTime;
	private Integer serviceTimes;
	private Integer executedTimes;
	private String therapistUserId;
	private Date appointmentTime;
	private Integer isExecuted;
	private Integer isAppointmented;
	private Integer isAvailable;
	private Date createTime;
	private Date updateTime;
	private Integer isPackage;
	private Integer status;

	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setTreatmentRecordId(String treatmentRecordId){
		this.treatmentRecordId=treatmentRecordId;
	}
	public String getTreatmentRecordId(){
		return treatmentRecordId;
	}
	public void setMedicalServiceItemId(String medicalServiceItemId){
		this.medicalServiceItemId=medicalServiceItemId;
	}
	public String getMedicalServiceItemId(){
		return medicalServiceItemId;
	}
	public void setAppointmentId(String appointmentId){
		this.appointmentId=appointmentId;
	}
	public String getAppointmentId(){
		return appointmentId;
	}
	public void setProductId(String productId){
		this.productId=productId;
	}
	public String getProductId(){
		return productId;
	}
	public void setProductPackageId(String productPackageId){
		this.productPackageId=productPackageId;
	}
	public String getProductPackageId(){
		return productPackageId;
	}
	public void setClinicId(String clinicId){
		this.clinicId=clinicId;
	}
	public String getClinicId(){
		return clinicId;
	}
	public void setShopId(String shopId){
		this.shopId=shopId;
	}
	public String getShopId(){
		return shopId;
	}
	public void setPatientId(String patientId){
		this.patientId=patientId;
	}
	public String getPatientId(){
		return patientId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setProductPackageTitle(String productPackageTitle){
		this.productPackageTitle=productPackageTitle;
	}
	public String getProductPackageTitle(){
		return productPackageTitle;
	}
	public void setDoctorId(String doctorId){
		this.doctorId=doctorId;
	}
	public String getDoctorId(){
		return doctorId;
	}
	public void setOutpatientRecordCreateTime(Date outpatientRecordCreateTime){
		this.outpatientRecordCreateTime=outpatientRecordCreateTime;
	}
	public Date getOutpatientRecordCreateTime(){
		return outpatientRecordCreateTime;
	}
	public void setServiceTimes(Integer serviceTimes){
		this.serviceTimes=serviceTimes;
	}
	public Integer getServiceTimes(){
		return serviceTimes;
	}
	public void setExecutedTimes(Integer executedTimes){
		this.executedTimes=executedTimes;
	}
	public Integer getExecutedTimes(){
		return executedTimes;
	}
	public void setTherapistUserId(String therapistUserId){
		this.therapistUserId=therapistUserId;
	}
	public String getTherapistUserId(){
		return therapistUserId;
	}
	public void setAppointmentTime(Date appointmentTime){
		this.appointmentTime=appointmentTime;
	}
	public Date getAppointmentTime(){
		return appointmentTime;
	}
	public void setIsExecuted(Integer isExecuted){
		this.isExecuted=isExecuted;
	}
	public Integer getIsExecuted(){
		return isExecuted;
	}
	public void setIsAppointmented(Integer isAppointmented){
		this.isAppointmented=isAppointmented;
	}
	public Integer getIsAppointmented(){
		return isAppointmented;
	}
	public void setIsAvailable(Integer isAvailable){
		this.isAvailable=isAvailable;
	}
	public Integer getIsAvailable(){
		return isAvailable;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setIsPackage(Integer isPackage){
		this.isPackage=isPackage;
	}
	public Integer getIsPackage(){
		return isPackage;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
}

