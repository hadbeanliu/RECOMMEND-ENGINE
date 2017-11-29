//package com.rongji.cms.recommend.engine.common;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.MasterNotRunningException;
//import org.apache.hadoop.hbase.ZooKeeperConnectionException;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
//import org.apache.hadoop.hbase.regionserver.BloomType;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.JDOMException;
//import org.jdom.input.SAXBuilder;
//
//
//public class TableMappingToHbase {
//
//	private static String MappingFile = "base-hbase-mapping.xml";
//
//	private static Map<String, Map<String, HColumnDescriptor>> tableToFamilys = new HashMap<String, Map<String, HColumnDescriptor>>();
//
//	private Map<String, HColumnDescriptor> getOrCreateFamilys(String name) {
//
//		if (tableToFamilys.containsKey(name))
//			return tableToFamilys.get(name);
//		else {
//			Map<String, HColumnDescriptor> familys = new HashMap<String, HColumnDescriptor>();
//			tableToFamilys.put(name, familys);
//			return familys;
//		}
//	}
//
//	private HColumnDescriptor getOrCreateCol(String name,Map<String, HColumnDescriptor> hCols) {
//		if (hCols.containsKey(name))
//			return hCols.get(name);
//		else {
//
//			HColumnDescriptor hCol = new HColumnDescriptor(name);
//			hCols.put(name, hCol);
//			return hCol;
//		}
//	}
//
//	public HColumnDescriptor build(String tableName, String  familyName, String
//		         compression, String  blockCache, String  blockSize, String 
//		         bloomFilter,String maxVersions, String  timeToLive, String 
//		         inMemory){
//		         
//		  		Map<String, HColumnDescriptor> familys=getOrCreateFamilys(tableName);
//		  		HColumnDescriptor columnDescriptor=getOrCreateCol(familyName, familys);
//		           
//		           if(compression != null)
//		              columnDescriptor.setCompressionType(Algorithm.valueOf(compression));
//		           if(blockCache != null)
//		              columnDescriptor.setBlockCacheEnabled(Boolean.parseBoolean(blockCache));
//		           if(blockSize != null)
//		              columnDescriptor.setBlocksize(Integer.parseInt(blockSize));
//		           if(bloomFilter != null)
//		              columnDescriptor.setBloomFilterType(BloomType.valueOf(bloomFilter));
//		           if(maxVersions != null)
//		              columnDescriptor.setMaxVersions(Integer.parseInt(maxVersions));
//		           if(timeToLive != null)
//		              columnDescriptor.setTimeToLive(Integer.parseInt(timeToLive));
//		           if(inMemory != null)
//		              columnDescriptor.setInMemory(Boolean.parseBoolean(inMemory));  
//		           return columnDescriptor;
//		         
//		      }	/**
//	 * @param args
//		     * @throws IOException 
//		     * @throws JDOMException 
//	 */
//	
//	  public  Map<String,HTableDescriptor> readMappingFile(String fileName) throws JDOMException, IOException{
//			    String mappingFile=MappingFile;
//		        if(fileName!=null)
//		        	mappingFile=fileName;
//		                  
//		        SAXBuilder build=new SAXBuilder();
//		        
//		        Document doc=build.build(TableMappingToHbase.class.getClassLoader().getResourceAsStream(mappingFile));
//		        
//		        Element root=doc.getRootElement();
//		        
//		        List<Element> tables=root.getChildren("table");
//		      
//		        Map<String,HTableDescriptor> descs=new HashMap<String,HTableDescriptor>();
//		        
//		        for(int i=0;i<tables.size();i++){
//		          
//		        	Element table=tables.get(i);
//		            
//		            String tableName=table.getAttributeValue("name");
//		            
//		            HTableDescriptor desc=new HTableDescriptor(tableName);
//		            
//		            String keyClass=table.getAttributeValue("keyClass");
//		            
//		            List<Element> fields=table.getChildren("family");
//		            
//		            for(int j=0;j<fields.size();j++){
//		   
//		                Element family=fields.get(j);
//		                String familyName  = family.getAttributeValue("name");
//		                String compression = family.getAttributeValue("compression");
//		                String blockCache  = family.getAttributeValue("blockCache");
//		                String blockSize   = family.getAttributeValue("blockSize");
//		                String bloomFilter = family.getAttributeValue("bloomFilter");
//		                String maxVersions = family.getAttributeValue("maxVersions");
//		                String timeToLive  = family.getAttributeValue("timeToLive");
//		                String inMemory    = family.getAttributeValue("inMemory");
//		                
//		                HColumnDescriptor hcol= build(tableName, familyName, compression, blockCache, blockSize, bloomFilter, maxVersions, timeToLive, inMemory);
////		             println(tableName, familyName, compression, blockCache, blockSize, bloomFilter, maxVersions, timeToLive, inMemory)
//		                desc.addFamily(hcol);
//		            }
//		            descs.put(tableName, desc);
//		          
//		        }
//		        
//		     return descs;
//		        
//		  }     
//		        
//		  public void  createTable(HTableDescriptor desc,Configuration conf){
//		    
//		    String name=conf.get("preferred.table.name",Bytes.toString(desc.getName()));
//		    
//		    HBaseAdmin admin = null;
//			try {
//				admin = new HBaseAdmin(conf);
//				 if(!(admin.tableExists(name)||admin.isTableDisabled(name))){
//			          desc.setName(name.getBytes());
//			          admin.createTable(desc);
//			          admin.flush(name);
//			    }
//			} catch (MasterNotRunningException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ZooKeeperConnectionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally{
//				 try {
//					admin.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		  }
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
