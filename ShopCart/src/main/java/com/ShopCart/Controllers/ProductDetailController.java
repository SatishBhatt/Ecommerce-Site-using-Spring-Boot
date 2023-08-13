package com.ShopCart.Controllers;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ShopCart.Repository.ProductDetailRepository;
import com.ShopCart.Service.AddProductService;
import com.ShopCart.Service.ProductDetailService;
import com.ShopCart.Service.PurchaseService;
import com.ShopCart.beans.AddProduct;
import com.ShopCart.beans.ProductDetail;
import com.ShopCart.beans.Purchase;

@Controller
public class ProductDetailController {

	@Autowired
	private ProductDetailService pd_service;
	@Autowired
	private ProductDetailRepository pd_repo;
	
	@Autowired
	private PurchaseService purchase_Service;

	@Autowired
	private AddProductService pro_Service;
	
	
	//handler to handle list productDetail and return model and view
	
		@RequestMapping("/viewdetailpro")
		public String listproductDetail(Model model,@RequestParam("id") Long id) {
			
			model.addAttribute("detail", purchase_Service.getPurchaseById(id));
			model.addAttribute("productDetails", pd_service.getAllProductDetail());

			model.addAttribute("productDetail",new ProductDetail());


			
			List<AddProduct> pro= pro_Service.getAllProduct();
			model.addAttribute("products", pro);
			
			
			
			return "Admin/productDetail";
		}
		
		
//		//handler to add new productDetail and view it on table
//		@PostMapping("/addproductDetail")
//		public String addProductDetail(@ModelAttribute Purchase purchase,
//				@ModelAttribute ProductDetail productDetail, @RequestParam("id") Long purchaseId) {
//
//		Purchase p =purchase_Service.getPurchaseById(purchaseId);
//		//p.getProductDetail().toString();
//		
//		
//		
//		System.out.println(purchase_Service.getPurchaseById(purchaseId));
//		
//		System.out.println(p.getProductDetail().toString());
//
//		    return "redirect:/viewproductDetail?id=" + purchaseId;
//		}

		@PostMapping("/addproductDetail")
		public String addProductDetail(@ModelAttribute Purchase purchase,@ModelAttribute ProductDetail productDetail,HttpServletRequest request,
		        @RequestParam("id") Long purchaseId) {
		    

		    String[] proName = request.getParameterValues("productName");
		    String[] quantities = request.getParameterValues("quantity");
		    String[] costs = request.getParameterValues("cost");
		    String[] taxes = request.getParameterValues("tax");
		    String[] amounts = request.getParameterValues("amount");

		    if (quantities != null && costs != null && taxes != null && amounts != null) {
		        for (int i = 0; i < quantities.length; i++) {
		            ProductDetail additionalDetail = new ProductDetail();

		            Long productNameId = Long.parseLong(proName[i]);
		            AddProduct productName = pro_Service.getProductById(productNameId);
		            additionalDetail.setAddProduct(productName);
		            additionalDetail.setQuantity(Long.parseLong(quantities[i]));
		            additionalDetail.setCost(Double.parseDouble(costs[i]));
		            additionalDetail.setTax(Double.parseDouble(taxes[i]));
		            additionalDetail.setAmount(Double.parseDouble(amounts[i]));
		            additionalDetail.setPurchase(purchase);

		            // Save the additional form data
		            pd_repo.save(additionalDetail);
		            
		        }
		    }
			
			

		    return "redirect:/viewproductDetail?id=" + purchaseId;
		}




		
		
		
		
		
		
		
		
		@RequestMapping("/viewproductDetail")
		public String viewproductDetail(Model model,@RequestParam("id") Long purchaseId) {
			model.addAttribute("detail", purchase_Service.getPurchaseById(purchaseId));
			model.addAttribute("productDetails", pd_service.getAllProductDetail());
			model.addAttribute("productDetail",new ProductDetail());
			
			List<AddProduct> pro= pro_Service.getAllProduct();
			model.addAttribute("products", pro);
			return "Admin/productDetail";
		}
		
//		//handler to update the productDetail
		@GetMapping("/updateproductDetail")
		public String updateproductDetail(Model model,@ModelAttribute  ProductDetail productDetail,
				@RequestParam ("id") Long id,BindingResult result,@RequestParam("id2") Long purchaseId) {
			model.addAttribute("detail", purchase_Service.getPurchaseById(purchaseId));
			model.addAttribute("productDetail",pd_service.getProductDetailById(id));
			model.addAttribute("productDetails" ,pd_service.getAllProductDetail() );
			
		
		List<AddProduct> pro= pro_Service.getAllProduct();
		model.addAttribute("products", pro);
		
		return "Admin/productDetail";
		}
		
		
		
		
//			return "Admin/productDetail";
//		}

			@GetMapping("/deleteproductDetail")
			public String deleteproductDetail(Model model,@ModelAttribute  ProductDetail productDetail,
					BindingResult result,@RequestParam("id2") Long purchaseId) {
				model.addAttribute("detail", purchase_Service.getPurchaseById(purchaseId));
				
				pd_repo.delete(productDetail);
				model.addAttribute("productDetails" ,pd_service.getAllProductDetail() );

				List<AddProduct> pro= pro_Service.getAllProduct();
				model.addAttribute("products", pro);
				
				return "Admin/productDetail";
			}
			
			
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			
			
			
//*****************************************handler for the st0ckview*************
			@RequestMapping("/goStock")
			public String Stock(Model model) {
				model.addAttribute("productDetails", pd_service.getAllProductDetail());
				
				List<ProductDetail> pro= pd_service.getAllProductDetail();
				model.addAttribute("addpro", pro);
				
				return "Admin/stockview";
			}
	//******************************************handler for view button on stock view
			@RequestMapping("/stockview")
			public String viewprod(Model model,@RequestParam("id") Long id) {
				ProductDetail pro= pd_service.getProductDetailById(id);
				model.addAttribute("data", pro);
				return "Admin/viewDetail";
			}
}
