<template>
  <v-card
          class="mt-4 mx-auto"
          max-width="400"
  >
    <v-sheet
            class="v-sheet--offset mx-auto"
            color="cyan"
            elevation="12"
            max-width="calc(100% - 32px)"
    >

      <apexchart type="bar" height="350" :options="chartOptions" :series="series"></apexchart>

    </v-sheet>

    <v-card-text class="pt-0">
      <div class="title font-weight-light mb-2">User Registrations</div>
      <div class="subheading font-weight-light grey--text">Last Campaign Performance</div>
      <v-divider class="my-2"></v-divider>
      <v-icon
              class="mr-2"
              small
      >
        mdi-clock
      </v-icon>
      <span class="caption grey--text font-weight-light">last registration 26 minutes ago</span>
    </v-card-text>
  </v-card>
</template>

<script>
  import VueApexCharts from 'vue-apexcharts'

  export default {
    name: 'HelloWorld',
    props: {
      msg: String
    },

    components: {
      apexchart: VueApexCharts,
    },

    data() {
      return {
        series: [{
          name: 'Net Profit',
          data: [44, 55, 57, 56, 61, 58, 63, 60, 66]
        }, {
          name: 'Revenue',
          data: [76, 85, 101, 98, 87, 105, 91, 114, 94]
        }, {
          name: 'Free Cash Flow',
          data: [35, 41, 36, 26, 45, 48, 52, 53, 41]
        }],
        chartOptions: {
          chart: {
            type: 'bar',
            height: 350,
            toolbar: {
              show: false
            },
          },
          plotOptions: {
            bar: {
              horizontal: false,
              columnWidth: '85%',
              endingShape: 'rounded'
            },
          },
          dataLabels: {
            enabled: false
          },
          stroke: {
            show: true,
            width: 2,
            colors: ['transparent']
          },
          xaxis: {
            categories: ['Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
          },
          yaxis: {
            title: {
              text: '$ (thousands)'
            }
          },
          fill: {
            opacity: 1
          },
          tooltip: {
            y: {
              formatter: function (val) {
                return "$ " + val + " thousands"
              }
            }
          },

          labels: [
            '12am',
            '3am',
            '6am',
            '9am',
            '12pm',
            '3pm',
            '6pm',
            '9pm',
          ],
          value: [
            200,
            675,
            410,
            390,
            310,
            460,
            250,
            240,
          ],

        },
      }
    }
  }

</script>

<style>
  .v-sheet--offset {
    top: -24px;
    position: relative;
  }
</style>
