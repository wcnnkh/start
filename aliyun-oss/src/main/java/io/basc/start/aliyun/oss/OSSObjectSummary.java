package io.basc.start.aliyun.oss;

import java.io.Serializable;
import java.util.Date;

import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.Owner;

/**
 * 因为SDK中此类没有实现序列化接口
 * 也可以直接继承com.aliyun.oss.model.OSSObjectSummary再实现序列化
 * @author shuchaowen
 *
 */
public final class OSSObjectSummary implements Serializable{
	private static final long serialVersionUID = 1L;

	/** The name of the bucket in which this object is stored */
    private String bucketName;

    /** The key under which this object is stored */
    private String key;

    private String eTag;

    private long size;

    private Date lastModified;

    private String storageClass;

    private Owner owner;

    /**
     * Constructor.
     */
    public OSSObjectSummary() {
    }
    
    public OSSObjectSummary(com.aliyun.oss.model.OSSObjectSummary ossObjectSummary){
    	this.bucketName = ossObjectSummary.getBucketName();
    	this.key = ossObjectSummary.getKey();
    	this.eTag = ossObjectSummary.getETag();
    	this.size = ossObjectSummary.getSize();
    	this.lastModified = ossObjectSummary.getLastModified();
    	this.storageClass = ossObjectSummary.getStorageClass();
    	this.owner = ossObjectSummary.getOwner();
    }

    /**
     * Gets the {@link Bucket} name.
     * 
     * @return The bucket name.
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * Sets the {@link Bucket} name.
     * 
     * @param bucketName
     *            The {@link Bucket} name.
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Gets the object key.
     * 
     * @return Object key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the object key.
     * 
     * @param key
     *            Object key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the object ETag. ETag is a 128bit MD5 signature about the object in
     * hex.
     * 
     * @return ETag value.
     */
    public String getETag() {
        return eTag;
    }

    /**
     * Sets the object ETag.
     * 
     * @param eTag
     *            ETag value.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Gets the object Size
     * 
     * @return Object size.
     */
    public long getSize() {
        return size;
    }

    /**
     * Sets the object size.
     * 
     * @param size
     *            Object size.
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * Gets the last modified time of the object.
     * 
     * @return The last modified time.
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * Sets the last modified time.
     * 
     * @param lastModified
     *            Last modified time.
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets the owner of the object.
     * 
     * @return Object owner.
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the object.
     * 
     * @param owner
     *            Object owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Gets the storage class of the object.
     * 
     * @return Object storage class.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storage class of the object.
     * 
     * @param storageClass
     *            Object storage class.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

}

