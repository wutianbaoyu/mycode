package qizi_zoufa;

public class Qizi {

	int newX, newY, oldX, oldY;
	int[][] pos;
	String color;

	public boolean ma_Rule() {

		try {
			if ((newX == oldX + 1 || newX == oldX - 1) && newY == oldY + 2
					&& pos[oldX][oldY + 1] == 0) {
				return true;
			}
			if ((newX == oldX + 1 || newX == oldX - 1) && newY == oldY - 2
					&& pos[oldX][oldY - 1] == 0) {
				return true;
			}
			if ((newY == oldY + 1 || newY == oldY - 1) && newX == oldX - 2
					&& pos[oldX - 1][oldY] == 0) {
				return true;
			}
			if ((newY == oldY + 1 || newY == oldY - 1) && newX == oldX + 2
					&& pos[oldX + 1][oldY] == 0) {
				return true;
			}

		} catch (Exception e) {
			
		}

		return false;

	}

	public boolean shi_Rule() {// 士
		if (newX > 6 && newX < 10 && newY > 2 && newY < 6) {// 九宫内移动
			if ((newX == oldX + 1 || newX == oldX - 1)
					&& (newY == oldY + 1 || newY == oldY - 1)) {
				return true;
			}
		}
		return false;
	}

	public boolean che_Rule() {// 车

		try {

			if (oldX == newX) {

				if (newY > oldY) {

					for (int i = oldY + 1; i < newY; i++) {

						if (pos[oldX][i] != 0) {
							return false;
						}
					}
					return true;
				} else if (newY < oldY) {
					for (int i = newY + 1; i < oldY; i++) {

						if (pos[oldX][i] != 0) {
							return false;
						}
					}
					return true;
				}
			} else if (newY == oldY) {

				if (newX > oldX) {

					for (int i = oldX + 1; i < newX; i++) {

						if (pos[i][oldY] != 0) {
							return false;
						}
					}
					return true;
				} else if (newX < oldX) {
					for (int i = newX + 1; i < oldX; i++) {

						if (pos[i][oldY] != 0) {
							return false;
						}
					}
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;
	}

	public boolean xiang_Rule() { // 象

		try {
			if (newX == oldX + 2 && newY == oldY + 2) {

				if (pos[oldX + 1][oldY + 1] == 0) {
					if (newX >= 5) {
						return true;
					}
				}
			}
			if (newX == oldX + 2 && newY == oldY - 2) {

				if (pos[oldX + 1][oldY - 1] == 0) {
					if (newX >= 5) {
						return true;
					}
				}
			}
			if (newX == oldX - 2 && newY == oldY + 2) {

				if (pos[oldX - 1][oldY + 1] == 0) {
					if (newX >= 5) {
						return true;
					}
				}
			}
			if (newX == oldX - 2 && newY == oldY - 2) {

				if (pos[oldX - 1][oldY - 1] == 0) {
					if (newX >= 5) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		return false;

	}

	public boolean shuai_Rule() {// 帅

		if (newX > 6 && newX < 10 && newY > 2 && newY < 6) {// 九宫内移动
			if (oldX == newX) {

				if (oldY == newY - 1 || oldY == newY + 1) {

					return true;
				}
			} else if (oldY == newY) {

				if (oldX == newX - 1 || oldX == newX + 1) {

					return true;
				}
			}
		}

		return false;
	}

	public boolean pao_Rule() {// 炮

		int count = 0;
		try {

			if (oldX == newX) {

				if (newY > oldY) {

					for (int i = oldY + 1; i < newY; i++) {

						if (pos[oldX][i] != 0) {
							count++;
						}
					}
					if (count == 0) {// 中间无棋子

						if (pos[newX][newY] == 0) {

							return true;
						} else {

							return false;
						}
					} else if (count == 1) {
						if (pos[newX][newY] != 0) {
							return true;
						} else {
							return false;
						}
					} else if (count > 1) {

						return false;
					}
				} else if (newY < oldY) {
					count = 0;
					for (int i = newY + 1; i < oldY; i++) {

						if (pos[oldX][i] != 0) {
							count++;
						}
					}
					if (count == 0) {// 中间无棋子
						if (pos[newX][newY] == 0) {

							return true;
						} else {

							return false;
						}
					} else if (count == 1) {
						if (pos[newX][newY] != 0) {
							return true;
						} else {
							return false;
						}
					} else if (count > 1) {

						return false;
					}
				}
			} else if (newY == oldY) {

				if (newX > oldX) {
					count = 0;
					for (int i = oldX + 1; i < newX; i++) {

						if (pos[i][oldY] != 0) {
							count++;
						}
					}
					if (count == 0) {// 中间无棋子
						if (pos[newX][newY] == 0) {

							return true;
						} else {

							return false;
						}
					} else if (count == 1) {
						if (pos[newX][newY] != 0) {
							return true;
						} else {
							return false;
						}
					} else if (count > 1) {

						return false;
					}
				} else if (newX < oldX) {
					count = 0;
					for (int i = newX + 1; i < oldX; i++) {

						if (pos[i][oldY] != 0) {
							count++;
						}
					}
					if (count == 0) {// 中间无棋子
						if (pos[newX][newY] == 0) {

							return true;
						} else {

							return false;
						}
					} else if (count == 1) {
						if (pos[newX][newY] != 0) {
							return true;
						} else {
							return false;
						}
					} else if (count > 1) {

						return false;
					}
				}
			}
		} catch (Exception e) {

		}
		return false;

	}

	public boolean bing_Rule() {// 兵

		if (oldX <= 4) {
			if (oldX == newX) {

				if (oldY == newY - 1 || oldY == newY + 1) {

					return true;
				}
			} else if (oldY == newY) {

				if (oldX == newX + 1) {

					return true;
				}
			} 
		}else {
			if (oldY == newY) {

				if (oldX == newX + 1) {

					return true;
				}
			}
		}
		return false;

	}

	public boolean check(String color, int[][] pos, int newX, int newY,
			int oldX, int oldY) {

		int a = pos[oldX][oldY];
		this.pos = pos;
		this.newX = newX;
		this.newY = newY;
		this.oldX = oldX;
		this.oldY = oldY;
		this.color = color;
		if (color.equals("black")) {
			a = a - 16;
		}
		switch (a) {
		case 1:
		case 9:
			return che_Rule();
			
		case 2:
		case 8:
			return ma_Rule();
			
		case 3:
		case 7:
			return xiang_Rule();
			
		case 4:
		case 6:
			return shi_Rule();
			
		case 5:
			return shuai_Rule();
			
		case 10:
		case 11:
			return pao_Rule();
			
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
			return bing_Rule();

		default:
			break;
		}
		return false;
	}

}
