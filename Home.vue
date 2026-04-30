<template>
  <div class="dashboard-container">
    <!-- 欢迎区域 -->
    <div class="welcome-section">
      <h2>欢迎回来，{{ userName }}！</h2>
      <p>今天是 {{ currentDate }}，祝您工作愉快！</p>
    </div>

    <!-- 核心统计卡片 -->
    <div class="core-stats-section">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-card class="stat-card" shadow="hover">
            <div class="stat-content">
              <div class="stat-icon sales-icon">
                <el-icon><ShoppingBag /></el-icon>
              </div>
              <div class="stat-info">
                <h3 class="stat-title">今日销售额</h3>
                <p class="stat-value">¥{{ stats.sales }}</p>
                <p class="stat-change positive">+{{ stats.salesGrowth }}%</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="stat-card" shadow="hover">
            <div class="stat-content">
              <div class="stat-icon order-icon">
                <el-icon><Ticket /></el-icon>
              </div>
              <div class="stat-info">
                <h3 class="stat-title">今日订单</h3>
                <p class="stat-value">{{ stats.orders }}</p>
                <p class="stat-change positive">+{{ stats.orderGrowth }}%</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="stat-card" shadow="hover">
            <div class="stat-content">
              <div class="stat-icon member-icon">
                <el-icon><UserFilled /></el-icon>
              </div>
              <div class="stat-info">
                <h3 class="stat-title">今日新增会员</h3>
                <p class="stat-value">{{ stats.newMembers }}</p>
                <p class="stat-change positive">+{{ stats.memberGrowth }}%</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="stat-card" shadow="hover">
            <div class="stat-content">
              <div class="stat-icon service-icon">
                <el-icon><Service /></el-icon>
              </div>
              <div class="stat-info">
                <h3 class="stat-title">今日预约</h3>
                <p class="stat-value">{{ stats.bookings }}</p>
                <p class="stat-change negative">-{{ stats.bookingDecline }}%</p>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 更多统计卡片（可折叠） -->
    <div class="more-stats-section">
      <el-collapse v-model="activeNames">
        <el-collapse-item title="更多统计信息" name="1">
          <el-row :gutter="20">
            <el-col :span="6">
              <el-card class="stat-card" shadow="hover">
                <div class="stat-content">
                  <div class="stat-icon product-icon">
                    <el-icon><Goods /></el-icon>
                  </div>
                  <div class="stat-info">
                    <h3 class="stat-title">商品总数</h3>
                    <p class="stat-value">{{ stats.totalProducts }}</p>
                    <p class="stat-change positive">+{{ stats.productGrowth }}%</p>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="stat-card" shadow="hover">
                <div class="stat-content">
                  <div class="stat-icon stock-icon">
                    <el-icon><Box /></el-icon>
                  </div>
                  <div class="stat-info">
                    <h3 class="stat-title">库存预警</h3>
                    <p class="stat-value">{{ stats.lowStockProducts }}</p>
                    <p class="stat-change positive">+{{ stats.lowStockGrowth }}%</p>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="stat-card" shadow="hover">
                <div class="stat-content">
                  <div class="stat-icon pending-order-icon">
                    <el-icon><Timer /></el-icon>
                  </div>
                  <div class="stat-info">
                    <h3 class="stat-title">待付款订单</h3>
                    <p class="stat-value">{{ stats.pendingOrders }}</p>
                    <p class="stat-change positive">+{{ stats.pendingOrdersChange }}%</p>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="stat-card" shadow="hover">
                <div class="stat-content">
                  <div class="stat-icon completed-order-icon">
                    <el-icon><Check /></el-icon>
                  </div>
                  <div class="stat-info">
                    <h3 class="stat-title">已完成订单</h3>
                    <p class="stat-value">{{ stats.completedOrders }}</p>
                    <p class="stat-change positive">+{{ stats.completedOrdersChange }}%</p>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="stat-card" shadow="hover">
                <div class="stat-content">
                  <div class="stat-icon refund-order-icon">
                    <el-icon><Close /></el-icon>
                  </div>
                  <div class="stat-info">
                    <h3 class="stat-title">退款订单</h3>
                    <p class="stat-value">{{ stats.refundOrders }}</p>
                    <p class="stat-change negative">{{ stats.refundOrdersChange }}%</p>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="stat-card" shadow="hover">
                <div class="stat-content">
                  <div class="stat-icon total-member-icon">
                    <el-icon><User /></el-icon>
                  </div>
                  <div class="stat-info">
                    <h3 class="stat-title">会员总数</h3>
                    <p class="stat-value">{{ stats.totalMembers }}</p>
                    <p class="stat-change positive">+{{ stats.membersGrowth }}%</p>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="stat-card" shadow="hover">
                <div class="stat-content">
                  <div class="stat-icon active-member-icon">
                    <el-icon><UserFilled /></el-icon>
                  </div>
                  <div class="stat-info">
                    <h3 class="stat-title">活跃会员</h3>
                    <p class="stat-value">{{ stats.activeMembers }}</p>
                    <p class="stat-change positive">+{{ stats.activeMembersChange }}%</p>
                  </div>
                </div>
              </el-card>
            </el-col>
            <el-col :span="6">
              <el-card class="stat-card" shadow="hover">
                <div class="stat-content">
                  <div class="stat-icon service-total-icon">
                    <el-icon><Ticket /></el-icon>
                  </div>
                  <div class="stat-info">
                    <h3 class="stat-title">服务总数</h3>
                    <p class="stat-value">{{ stats.totalServices }}</p>
                    <p class="stat-change positive">+{{ stats.servicesGrowth }}%</p>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </div>

    <!-- 图表区域 -->
    <div class="charts-section">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card class="chart-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <span>销售趋势</span>
                <el-select v-model="chartRange" size="small" class="range-select">
                  <el-option label="近7天" value="7d"></el-option>
                  <el-option label="近30天" value="30d"></el-option>
                  <el-option label="近90天" value="90d"></el-option>
                </el-select>
              </div>
            </template>
            <div class="chart-content">
              <div class="chart-placeholder">
                <el-icon class="chart-icon-large"><DataAnalysis /></el-icon>
                <p>销售趋势图表</p>
                <p class="chart-tip">提示：实际项目中可集成ECharts等图表库</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="chart-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <span>订单状态分布</span>
              </div>
            </template>
            <div class="chart-content">
              <div class="chart-placeholder">
                <el-icon class="chart-icon-large"><PieChart /></el-icon>
                <p>订单状态分布图表</p>
                <p class="chart-tip">提示：实际项目中可集成ECharts等图表库</p>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 最近订单 -->
    <div class="recent-orders">
      <el-card shadow="hover">
        <template #header>
          <div class="card-header">
            <span>最近订单</span>
            <el-button type="primary" size="small" plain>
              查看全部
            </el-button>
          </div>
        </template>
        <el-table :data="recentOrders" style="width: 100%">
          <el-table-column prop="orderId" label="订单编号" width="180"></el-table-column>
          <el-table-column prop="customerName" label="客户名称"></el-table-column>
          <el-table-column prop="totalPrice" label="订单金额" width="100">
            <template #default="scope">
              ¥{{ scope.row.totalPrice }}
            </template>
          </el-table-column>
          <el-table-column prop="status" label="订单状态" width="120">
            <template #default="scope">
              <el-tag :type="getStatusType(scope.row.status)">
                {{ scope.row.status }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="180"></el-table-column>
          <el-table-column label="操作" width="180">
            <template #default="scope">
              <el-button type="primary" size="small" plain @click="handleViewOrder(scope.row)">
                查看
              </el-button>
              <el-button size="small" plain @click="handleProcessOrder(scope.row)">
                处理
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import {
  ShoppingBag,
  Ticket,
  UserFilled,
  Service,
  DataAnalysis,
  PieChart,
  Star,
  Coin,
  Timer,
  Check,
  StarFilled,
  Goods,
  Box,
  Close,
  User
} from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

// 响应式数据
const userName = ref('管理员')
const chartRange = ref('7d')
const activeNames = ref([])

// 统计数据
const stats = ref({
  sales: 12800,
  salesGrowth: 12.5,
  orders: 86,
  orderGrowth: 8.2,
  newMembers: 12,
  memberGrowth: 5.3,
  bookings: 8,
  bookingDecline: 2.1,
  totalProducts: 256,
  productGrowth: 15.8,
  seckillProducts: 12,
  seckillGrowth: 25.0,
  lowStockProducts: 8,
  lowStockGrowth: 12.5,
  pendingOrders: 15,
  pendingOrdersChange: 5.2,
  completedOrders: 71,
  completedOrdersChange: 10.5,
  refundOrders: 3,
  refundOrdersChange: -2.1,
  totalMembers: 1250,
  membersGrowth: 8.5,
  activeMembers: 890,
  activeMembersChange: 12.3,
  vipMembers: 320,
  vipMembersChange: 15.8,
  totalPoints: 56800,
  pointsGrowth: 20.5,
  totalServices: 15,
  servicesGrowth: 10.5,
  pendingBookings: 12,
  pendingBookingsChange: 8.2,
  completedBookings: 65,
  completedBookingsChange: 15.3,
  serviceEvaluations: 45,
  serviceEvaluationsChange: 20.8,
  averageEvaluationScore: 4.8
})

// 最近订单数据
const recentOrders = ref([
  {
    orderId: '20260130001',
    customerName: '张三',
    totalPrice: 299,
    status: '待付款',
    createTime: '2026-01-30 14:30:00'
  },
  {
    orderId: '20260130002',
    customerName: '李四',
    totalPrice: 599,
    status: '已付款',
    createTime: '2026-01-30 14:25:00'
  },
  {
    orderId: '20260130003',
    customerName: '王五',
    totalPrice: 199,
    status: '已发货',
    createTime: '2026-01-30 14:20:00'
  },
  {
    orderId: '20260130004',
    customerName: '赵六',
    totalPrice: 899,
    status: '已完成',
    createTime: '2026-01-30 14:15:00'
  },
  {
    orderId: '20260130005',
    customerName: '钱七',
    totalPrice: 399,
    status: '已取消',
    createTime: '2026-01-30 14:10:00'
  },
  {
    orderId: '20260130006',
    customerName: '孙八',
    totalPrice: 699,
    status: '退款中',
    createTime: '2026-01-30 14:05:00'
  },
  {
    orderId: '20260130007',
    customerName: '周九',
    totalPrice: 499,
    status: '已退款',
    createTime: '2026-01-30 14:00:00'
  }
])

// 计算属性 - 缓存日期计算结果
const currentDate = ref('')

// 初始化日期
const initDate = () => {
  const now = new Date()
  const year = now.getFullYear()
  const month = String(now.getMonth() + 1).padStart(2, '0')
  const day = String(now.getDate()).padStart(2, '0')
  const weekday = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'][now.getDay()]
  currentDate.value = `${year}-${month}-${day} ${weekday}`
}

// 只在组件挂载时初始化日期，避免频繁计算
onMounted(() => {
  initDate()
  // 初始化数据
  console.log('仪表盘初始化')
})

// 方法
const statusMap = {
  '待付款': 'warning',
  '已付款': 'primary',
  '已发货': 'info',
  '已完成': 'success',
  '已取消': 'danger',
  '退款中': 'warning',
  '已退款': 'danger'
}

const getStatusType = (status) => {
  return statusMap[status] || 'default'
}

// 订单操作方法
const handleViewOrder = (order) => {
  ElMessage.info(`查看订单：${order.orderId}`)
}

const handleProcessOrder = (order) => {
  ElMessage.info(`处理订单：${order.orderId}`)
}

// 生命周期 - 已在上面定义
</script>

<style scoped>
.dashboard-container {
  padding: 20px;
}

/* 欢迎区域 */
.welcome-section {
  margin-bottom: 20px;
}

.welcome-section h2 {
  margin: 0 0 10px 0;
  font-size: 24px;
  color: #333;
}

.welcome-section p {
  margin: 0;
  font-size: 14px;
  color: #666;
}

/* 核心统计卡片区域 */
.core-stats-section {
  margin-bottom: 20px;
}

/* 更多统计卡片区域 */
.more-stats-section {
  margin-bottom: 20px;
}

.more-stats-section :deep(.el-collapse) {
  background: #ffffff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.more-stats-section :deep(.el-collapse-item__header) {
  background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
  border-bottom: 1px solid #dee2e6;
}

.more-stats-section :deep(.el-collapse-item__content) {
  padding: 16px;
}

/* 统计卡片样式 */
.stat-card {
  height: 120px;
  transition: all 0.3s ease;
}

.stat-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1) !important;
  transform: translateY(-2px);
}

.stat-content {
  display: flex;
  align-items: center;
  height: 100%;
}

.stat-icon {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  margin-right: 16px;
  transition: all 0.3s ease;
}

.stat-card:hover .stat-icon {
  transform: scale(1.05);
}

.sales-icon {
  background-color: rgba(64, 158, 255, 0.1);
  color: #409EFF;
}

.order-icon {
  background-color: rgba(103, 194, 58, 0.1);
  color: #67C23A;
}

.member-icon {
  background-color: rgba(230, 162, 60, 0.1);
  color: #E6A23C;
}

.service-icon {
  background-color: rgba(245, 108, 108, 0.1);
  color: #F56C6C;
}

.service-total-icon {
  background-color: rgba(64, 158, 255, 0.1);
  color: #409EFF;
}

.product-icon {
  background-color: rgba(102, 16, 242, 0.1);
  color: #6610F2;
}

.stock-icon {
  background-color: rgba(0, 188, 212, 0.1);
  color: #00BCD4;
}

.pending-order-icon {
  background-color: rgba(255, 152, 0, 0.1);
  color: #FF9800;
}

.completed-order-icon {
  background-color: rgba(76, 175, 80, 0.1);
  color: #4CAF50;
}

.refund-order-icon {
  background-color: rgba(244, 67, 54, 0.1);
  color: #F44336;
}

.total-member-icon {
  background-color: rgba(64, 158, 255, 0.1);
  color: #409EFF;
}

.active-member-icon {
  background-color: rgba(103, 194, 58, 0.1);
  color: #67C23A;
}

.stat-info {
  flex: 1;
}

.stat-title {
  font-size: 14px;
  color: #666;
  margin: 0 0 8px 0;
}

.stat-value {
  font-size: 20px;
  font-weight: bold;
  color: #333;
  margin: 0 0 4px 0;
  transition: all 0.3s ease;
}

.stat-card:hover .stat-value {
  transform: translateY(-2px);
}

.stat-change {
  font-size: 12px;
  margin: 0;
  transition: all 0.3s ease;
}

.positive {
  color: #67C23A;
}

.negative {
  color: #F56C6C;
}

/* 图表区域 */
.charts-section {
  margin-bottom: 20px;
}

.chart-card {
  height: 300px;
  transition: all 0.3s ease;
}

.chart-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1) !important;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.range-select {
  width: 100px;
}

.chart-content {
  height: calc(100% - 40px);
  display: flex;
  align-items: center;
  justify-content: center;
}

.chart-placeholder {
  text-align: center;
  color: #999;
  transition: all 0.3s ease;
}

.chart-card:hover .chart-placeholder {
  transform: scale(1.02);
}

.chart-icon-large {
  font-size: 48px;
  margin-bottom: 16px;
  transition: all 0.3s ease;
}

.chart-card:hover .chart-icon-large {
  transform: rotate(5deg);
}

.chart-tip {
  font-size: 12px;
  color: #ccc;
  margin-top: 8px;
}

/* 最近订单区域 */
.recent-orders {
  margin-bottom: 20px;
}

.recent-orders :deep(.el-card) {
  transition: all 0.3s ease;
}

.recent-orders :deep(.el-card:hover) {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1) !important;
}

.recent-orders :deep(.el-table) {
  border-radius: 8px;
  overflow: hidden;
}

.recent-orders :deep(.el-table__header-wrapper) {
  background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
}

.recent-orders :deep(.el-table th) {
  font-weight: 600;
  color: #333;
}

.recent-orders :deep(.el-table td) {
  color: #666;
}

/* 操作按钮样式 */
.recent-orders :deep(.el-button) {
  transition: all 0.3s ease;
}

.recent-orders :deep(.el-button:hover) {
  transform: translateY(-1px);
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .stat-card {
    height: 100px;
  }
  
  .stat-icon {
    width: 50px;
    height: 50px;
    font-size: 20px;
  }
  
  .stat-value {
    font-size: 18px;
  }
}

@media (max-width: 768px) {
  .dashboard-container {
    padding: 16px;
  }
  
  .welcome-section h2 {
    font-size: 20px;
  }
  
  .stat-card {
    height: 80px;
  }
  
  .stat-icon {
    width: 40px;
    height: 40px;
    font-size: 16px;
  }
  
  .stat-value {
    font-size: 16px;
  }
  
  .chart-card {
    height: 250px;
  }
  
  .chart-icon-large {
    font-size: 36px;
  }
}
</style>