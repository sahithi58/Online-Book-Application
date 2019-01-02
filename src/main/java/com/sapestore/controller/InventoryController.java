package com.sapestore.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.BookService;
import com.sapestore.service.InventoryService;
import com.sapestore.vo.BookVO;

/**
 * This is a controller class for updating inventory.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Controller
@SessionAttributes({"numberOfRecords","sorting"})
public class InventoryController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(InventoryController.class.getName());

	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private BookService bookservice;

	@Autowired(required = false)
	ServletContext servletContext;

	/**
	 * Processes the inventory updation requests.
	 * 
	 * @param updateInventory
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateInventory", method = RequestMethod.POST)
	public String updateInventory(@ModelAttribute("updateInv") BookVO updateInventory, ModelMap modelMap)
			throws SapeStoreException {
		LOGGER.debug("updateInventory method: START");
		String forwardStr = null;
		String thumbPath = null;
		String fullPath = null;
		File thumbUploadDir = null;
		File fullUploadDir = null;
		try {
			thumbPath = servletContext.getRealPath(ApplicationConstants.THUMB_IMG_URL);
			fullPath = servletContext.getRealPath(ApplicationConstants.FULL_IMG_URL);
			System.out.println("thumb path: "+thumbPath);
			thumbUploadDir = new File(thumbPath);
			fullUploadDir = new File(fullPath);
			if (thumbUploadDir.exists() == false) {
				thumbUploadDir.mkdirs();
			}
			System.out.println("thumbUploadDir: "+thumbUploadDir);
			if (fullUploadDir.exists() == false) {
				fullUploadDir.mkdirs();
			}
			if (null != updateInventory) {
				if (null != updateInventory.getThumbImage() && updateInventory.getThumbImage().getOriginalFilename() != "") {
					System.out.println("HERE: "+updateInventory.getThumbImage());
                    File thumbFile = new File(thumbPath, updateInventory.getThumbImage().getOriginalFilename());
                    System.out.println("updateInventory.getThumbImage().getOriginalFilename(): "+updateInventory.getThumbImage().getOriginalFilename());
                    System.out.println("updateInventory.getThumbImage(): "+updateInventory.getThumbImage());
                    byte[] bytes = updateInventory.getThumbImage().getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(thumbFile));
                    stream.write(bytes);
                    stream.close();
                    updateInventory.setThumbPath(ApplicationConstants.THUMB_IMG_URL +  updateInventory.getThumbImage().getOriginalFilename());
                }
                if (null != updateInventory.getFullImage() && updateInventory.getFullImage().getOriginalFilename() != "") {
                    File largeFile = new File(fullPath, updateInventory.getFullImage().getOriginalFilename());
                    byte[] bytes = updateInventory.getFullImage().getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(largeFile));
                    stream.write(bytes);
                    stream.close();
                    updateInventory.setFullPath(ApplicationConstants.FULL_IMG_URL + updateInventory.getFullImage().getOriginalFilename());
                }
				inventoryService.updateBooks(updateInventory);
				forwardStr = "redirect:/admin/manageInventory";
			} else {
				forwardStr = ApplicationConstants.FAILURE;
			}
		} catch (IOException e) {
			LOGGER.error("updateInventory method: ERROR: " + e);
			forwardStr = ApplicationConstants.FAILURE;
		}
		LOGGER.debug("updateInventory method: END");
		return forwardStr;
	}

	@RequestMapping(value = "/updateInventory", method = RequestMethod.POST, params = "manageInv")
	public String cancelUpdate(@ModelAttribute("updateInv") BookVO updateInventory, ModelMap modelMap)
			throws SapeStoreException {
		return "redirect:/manageInventory";
	}

	
	
	
	/**
	 * Processes the manage inventory page requests and returns the data for the
	 * page.
	 * 
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/manageInventory", method = RequestMethod.GET)
	public String manageInventory(ModelMap modelMap) throws Exception {
		LOGGER.debug("manageInventory method: START");
		long quantity=inventoryService.getRecordsQuantity();
		try {
			 if ((int)quantity != quantity) {
			        throw new ArithmeticException("integer overflow");
			    }
			modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryAuthor(1));
			modelMap.addAttribute("sorting", "category");
			modelMap.addAttribute("numberOfRecords", (int) quantity);
        	modelMap.addAttribute("page", 1);
		} catch (Exception ex) {
			LOGGER.error("manageInventory method: ERROR: " + ex);
			return ApplicationConstants.FAILURE;
		}
		LOGGER.debug("manageInventory method: END");
		
		
		return "ManageInventory";
	}
	
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String sortbycategory(ModelMap modelMap) throws SapeStoreException {

		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryCategory(1));
		modelMap.addAttribute("sorting", "category");
		return "ManageInventory";
	}
	@RequestMapping(value="/admin/viewbooks", method = RequestMethod.GET)  
    public String edit(@RequestParam(value="page",required=false) int pageid,
    		@ModelAttribute("sorting")String sortOrder,ModelMap modelMap) throws SapeStoreException{  
        int total=10;  
        int returnPageId = pageid;
        if(pageid==1){}  
        else{  
            pageid=(pageid-1)*total+1;  
        }  
        try {
        	if(sortOrder=="category"){
			modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryCategory(pageid));
        	}
        	else if (sortOrder=="bookname") {
        		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryBookName(pageid));
			}
        	else if (sortOrder=="author") {
        		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryAuthor(pageid));
			}
        	else if (sortOrder=="publisher") {
        		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryPublisher(pageid));
			}
        	else{
        		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryQuantity(pageid));
        	}
        	modelMap.addAttribute("page", returnPageId);
		} catch (Exception ex) {
			LOGGER.error("manageInventory method: ERROR: " + ex);
			return ApplicationConstants.FAILURE;
		}
		LOGGER.debug("manageInventory method: END");
		
        return "ManageInventory";  
    }  
	@RequestMapping(value = "/admin/bookname", method = RequestMethod.GET)
	public String sortbybookname(ModelMap modelMap) throws SapeStoreException {

		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryBookName(1));
		modelMap.addAttribute("sorting", "bookname");
		return "ManageInventory";
	}

	@RequestMapping(value = "/admin/author", method = RequestMethod.GET)
	public String sortbyauthorname(ModelMap modelMap) throws SapeStoreException {

		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryAuthor(1));
		modelMap.addAttribute("sorting", "author");
		return "ManageInventory";
	}

	@RequestMapping(value = "/admin/publisher", method = RequestMethod.GET)
	public String sortbypublishername(ModelMap modelMap) throws SapeStoreException {

		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryPublisher(1));
		modelMap.addAttribute("sorting", "publisher");
		return "ManageInventory";
	}

	@RequestMapping(value = "/admin/quantity", method = RequestMethod.GET)
	public String sortbyquantity(ModelMap modelMap) throws SapeStoreException {

		modelMap.addAttribute("adminInventoryList", inventoryService.getAdminInventoryQuantity(1));
		modelMap.addAttribute("sorting", "quantity");
		return "ManageInventory";
	}
}
