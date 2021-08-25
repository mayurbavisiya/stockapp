package com.nl.util;

import java.util.regex.Pattern;

import com.nl.beans.StockInputDTO;
import com.nl.exception.ValidationException;

public class Validation {

	final static String DOUBLE_PATTERN = "[0-9]{1,13}(\\.[0-9]*)?";
	final static String APLPHABET_REGEX = "^[a-zA-Z]*$";

	public static void validateUpdateStockRequest(StockInputDTO dto) throws ValidationException {
		if (dto == null) {
			throw new ValidationException("stockInputNullCode", "stockInputNullDesc");
		}

		else if (dto.getCurrentPrice() == null && dto.getStockName() == null) {
			throw new ValidationException("stockInputNullCode", "stockInputNullDesc");
		} else if (dto.getCurrentPrice() != null && "".equals(dto.getCurrentPrice())) {
			throw new ValidationException("emptyStockPriceCode", "emptyStockPriceDesc");
		}

		else if (dto.getStockName() != null && "".equals(dto.getStockName())) {
			throw new ValidationException("emptyStockNameCode", "emptyStockNameDesc");
		}

		else if (dto.getCurrentPrice() != null && !"".equals(dto.getCurrentPrice())) {
			final String DOUBLE_PATTERN = "[0-9]{1,13}(\\.[0-9]*)?";
			if (!Pattern.matches(DOUBLE_PATTERN, dto.getCurrentPrice())) {
				throw new ValidationException("invalidPriceCode", "invalidPriceDesc");
			}
		} else if (dto.getStockName() != null && !"".equals(dto.getStockName())) {
			final String APLPHABET_REGEX = "^[a-zA-Z]*$";
			if (!Pattern.matches(APLPHABET_REGEX, dto.getStockName())) {
				throw new ValidationException("invalidStockNameCode", "invalidStockNameDesc");
			}
		}
	}

	public static void validateCreateSTockRequest(StockInputDTO dto) throws ValidationException {
		if (dto == null) {
			throw new ValidationException("stockInputNullCode", "stockInputNullDesc");
		} else if (dto.getCurrentPrice() == null || "".equals(dto.getCurrentPrice())) {
			throw new ValidationException("emptyStockPriceCode", "emptyStockPriceDesc");
		} else if (dto.getStockName() == null || "".equals(dto.getStockName())) {
			throw new ValidationException("emptyStockNameCode", "emptyStockNameDesc");
		} else if (dto.getCurrentPrice() != null && !"".equals(dto.getCurrentPrice())) {

			if (!Pattern.matches(DOUBLE_PATTERN, dto.getCurrentPrice())) {
				throw new ValidationException("invalidPriceCode", "invalidPriceDesc");
			}
		} else if (dto.getStockName() != null && !"".equals(dto.getStockName())) {
			if (!Pattern.matches(APLPHABET_REGEX, dto.getStockName())) {
				throw new ValidationException("invalidStockNameCode", "invalidStockNameDesc");
			}
		}
	}
}
