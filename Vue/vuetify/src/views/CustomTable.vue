<e></e><template>
    <v-container>
        <v-row justify="center">
            <v-dialog v-model="dialog" persistent max-width="600px">
                <v-card>
                    <v-card-title>
                        <span class="headline">Registrar Ocorrência</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field v-model="newCategory" label="Categoria*" required></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field v-model="newLastUpdate" label="Data*" required></v-text-field>
                                </v-col>
                            </v-row>
                        </v-container>
                        <small>*indicates required field</small>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="closeAddIncident">Close</v-btn>
                        <v-btn color="blue darken-1" text @click="addIncident">Save</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>

        <v-row
                class="mb-6"
                justify="space-around"
                no-gutters
        >
            <v-col lg="2">
                <v-btn
                    v-if="showIncidents"
                    @click="showIncidents = !showIncidents"
                    small color="secondary"
                    class="m-4"
                >Esconder Ocorrências</v-btn>

                <v-btn
                    v-if="!showIncidents"
                    @click="showIncidents = !showIncidents"
                    small color="primary"
                    class="m-4"
                >Exibir Ocorrências</v-btn>
            </v-col>

            <v-col lg="2">
                <v-btn
                    v-if="showStatistics"
                    small color="secondary"
                    class="m-4"
                    @click="showStatistics = !showStatistics"
                >Esconder Estatísticas</v-btn>

                <v-btn
                    v-if="!showStatistics"
                    small color="primary"
                    class="m-4"
                    @click="showStatistics = !showStatistics"
                >Exibir Estatísticas</v-btn>
            </v-col>

        </v-row>

        <v-row>
            <v-col v-if="showIncidents">
                <v-container :class="`incidents-layout`">
                    <v-card  :class="`incidents-${testeCSS()}`" flat>
                        <v-container flat>
                            <v-row justify="space-between">
                                <v-col lg="8">
                                    <v-text-field
                                        v-model="search"
                                        append-icon="search"
                                        label="Pesquise uma ocorrência"
                                        single-line
                                        hide-details
                                    />
                                </v-col>

                                <v-col lg="2">
                                    <v-btn @click="dialog = !dialog" class="mx-2" fab dark color=#2E86C1>
                                        <v-icon>mdi-plus</v-icon>
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-container>

                        <v-row class="mt-n12" flat>
                            <v-col>
                                <v-switch
                                    v-model="switch1"
                                    label="ongoing"
                                    value="ongoing"
                                    color="#2E86C1"
                                    flat
                                    hide-details
                                />
                            </v-col>

                            <v-col>
                                <v-switch
                                        v-model="switch2"
                                        label="complete"
                                        value="complete"
                                        color="#2BBD43"
                                        flat
                                        hide-details
                                />
                            </v-col>

                            <v-col>
                                <v-switch
                                        v-model="switch3"
                                        label="overdue"
                                        value="overdue"
                                        color="#D8231B"
                                        flat
                                        hide-details
                                />
                            </v-col>
                        </v-row>

                        <v-expansion-panels>
                            <v-expansion-panel
                                v-for="incident in filteredIncidents"
                                :key="incident.id"
                                :class="`pa-3 incident ${incident.status}`"
                            >
                                <v-expansion-panel-header hide-actions>
                                    <v-layout row wrap>
                                        <v-flex xs12 md4>
                                            <div class="caption grey--text">Categoria</div>
                                            <div>{{incident.category}}</div>
                                        </v-flex>

                                        <v-flex xs6 sm4 md4>
                                            <div class="caption grey--text">Last Update</div>
                                            <div>{{incident.lastUpdate}}</div>
                                        </v-flex>

                                        <v-flex xs2 sm4 md4>
                                            <v-btn
                                                    :color="getColor(incident.status)"
                                                    :class="`white--text text-center`"
                                                    dark
                                                    width="90%"
                                                    small
                                            >
                                                {{incident.status}}
                                            </v-btn>
                                        </v-flex>

                                    </v-layout>
                                </v-expansion-panel-header>

                                <v-expansion-panel-content>
                                    <v-layout column wrap>
                                        <v-flex xs2 sm4 md2>
                                            <v-row
                                                    class="mb-6"
                                                    justify="end"
                                                    no-gutters
                                            >
                                                <v-col lg="2" v-for="s in getOtherStatus(incident.status)" :key="s">
                                                    <v-btn
                                                            @click="incident.status = s"
                                                            :color="getColor(s)"
                                                            :class="`white--text text-center`"
                                                            dark
                                                            small
                                                    >
                                                        {{s}}
                                                    </v-btn>
                                                </v-col>
                                            </v-row>
                                            <div class="caption grey--text">Descrição</div>
                                            <div class="caption">{{incident.details}}</div>
                                        </v-flex>
                                    </v-layout>
                                </v-expansion-panel-content>

                            </v-expansion-panel>
                        </v-expansion-panels>
                    </v-card>
                </v-container>
            </v-col>

            <v-col v-if="showStatistics">
                <v-container :class="`statistics-layout`">
                    <v-row :justify="end">
                        <v-col v-for="stat in stats.daily" :key="stat" md="4">
                            <v-card
                                    class="mx-auto"
                                    max-width="344"
                                    outlined
                            >
                                <v-card-text>
                                    <div class="display-1 text-center">{{stat.value}}</div>
                                    <div class="text-center">{{stat.label}}</div>
                                </v-card-text>
                            </v-card>
                        </v-col>

                        <v-col v-for="stat in stats.weekly" :key="stat" md="4">
                            <v-card
                                    class="mx-auto"
                                    max-width="344"
                                    outlined
                            >
                                <v-card-text>
                                    <div class="display-1 text-center">{{stat.value}}</div>
                                    <div class="text-center">{{stat.label}}</div>
                                </v-card-text>
                            </v-card>
                        </v-col>

                        <v-col v-for="stat in stats.monthly" :key="stat" md="4">
                            <v-card
                                    class="mx-auto"
                                    max-width="344"
                                    outlined
                            >
                                <v-card-text>
                                    <div class="display-1 text-center">{{stat.value}}</div>
                                    <div class="text-center">{{stat.label}}</div>
                                </v-card-text>
                            </v-card>
                        </v-col>
                    </v-row>
                    <v-row :justify="start">
                        <v-col>
                            <v-card max-width="80%">
                                <v-sparkline
                                        :value="graphTest"
                                        :gradient="gradient1"
                                        :smooth="radius || false"
                                        :padding="padding"
                                        :line-width="width"
                                        :stroke-linecap="lineCap"
                                        :gradient-direction="gradientDirection"
                                        :fill="fill"
                                        :type="type"
                                        :auto-line-width="autoLineWidth"
                                        auto-draw
                                />
                            </v-card>
                        </v-col>

                        <v-col>
                            <v-card max-width="80%">
                                <v-sparkline
                                        :value="graphTest"
                                        :gradient="gradient2"
                                        :smooth="radius || false"
                                        :padding="padding"
                                        :line-width="width"
                                        :stroke-linecap="lineCap"
                                        :gradient-direction="gradientDirection"
                                        :fill="fill"
                                        :type="type"
                                        :auto-line-width="autoLineWidth"
                                        auto-draw
                                />
                            </v-card>
                        </v-col>
                    </v-row>

                    <v-row :justify="end">
                        <v-col>
                            <v-card max-width="100%">
                                <v-card-text>
                                    <div class="font-weight-thin">Ocorrências Finalizadas (Semanal)</div>
                                </v-card-text>

                                <v-sparkline
                                        :value="graphTest"
                                        :gradient="gradient3"
                                        :smooth="radius || false"
                                        :padding="padding"
                                        :line-width="width"
                                        :stroke-linecap="lineCap"
                                        :gradient-direction="gradientDirection"
                                        :fill="fill"
                                        :type="type"
                                        :auto-line-width="autoLineWidth"
                                        auto-draw
                                />
                            </v-card>
                        </v-col>

                        <v-col>
                            <v-card max-width="100%">
                                <v-card-text>
                                    <div class="font-weight-thin">Ocorrências Finalizadas (Mensal)</div>
                                </v-card-text>

                                <v-sparkline
                                        :value="graphTest"
                                        :gradient="gradient3"
                                        :smooth="radius || false"
                                        :padding="padding"
                                        :line-width="width"
                                        :stroke-linecap="lineCap"
                                        :gradient-direction="gradientDirection"
                                        :fill="fill"
                                        :type="type"
                                        :auto-line-width="autoLineWidth"
                                        auto-draw
                                />
                            </v-card>
                        </v-col>

                        <v-col>
                            <v-card max-width="100%">
                                <v-card-text>
                                    <div class="font-weight-thin">Ocorrências Finalizadas (Semestral)</div>
                                </v-card-text>

                                <v-sparkline
                                    :value="graphTest"
                                    :gradient="gradient3"
                                    :smooth="radius || false"
                                    :padding="padding"
                                    :line-width="width"
                                    :stroke-linecap="lineCap"
                                    :gradient-direction="gradientDirection"
                                    :fill="fill"
                                    :type="type"
                                    :auto-line-width="autoLineWidth"
                                    auto-draw
                                />
                            </v-card>
                        </v-col>
                    </v-row>
                </v-container>
            </v-col>
        </v-row>

    </v-container>
</template>

<script>
export default {
    name: "CustomTable",
    data: function () {
        return {
            dialog: false,
            newId: 10,
            newCategory: '',
            newLastUpdate: '',

            status: ['ongoing', 'complete', 'overdue'],
            showStatusOptions: false,

            // Gráfico
            graphTest: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
            gradient1: ['#f72047', '#ffd200', '#1feaea'],
            gradient2: ['#00c6ff', '#F0F', '#FF0'],
            gradient3: ['#2ECC71', '#F0F3F4', '#34495E'],
            width: 2,
            radius: 10,
            padding: 8,
            lineCap: 'round',
            gradientDirection: 'top',
            fill: false,
            type: 'trend',
            autoLineWidth: false,

            pagination: {},
            search: '',
            switch1: 'ongoing',
            switch2: 'complete',
            switch3: 'overdue',
            showIncidents: true,
            showStatistics: true,

            stats: {
                daily: [
                    {label: 'Ocorrências Registradas (Diária)', value: 10},
                    {label: 'Ocorrências Respondidas (Diária)', value: 2},
                    {label: 'Ocorrências Finalizadas (Diária)', value: 1}
                ],

                weekly: [
                    {label: 'Ocorrências Registradas (Semanal)', value: 85},
                    {label: 'Ocorrências Respondidas (Semanal)', value: 70},
                    {label: 'Ocorrências Finalizadas (Semanal)', value: 50}
                ],

                monthly: [
                    {label: 'Ocorrências Registradas (Mensal)', value: 300},
                    {label: 'Ocorrências Respondidas (Mensal)', value: 250},
                    {label: 'Ocorrências Finalizadas (Mensal)', value: 245}
                ]

            },

            incidents: [
                {
                    id: 1,
                    category: 'Roubo',
                    status: 'complete',
                    lastUpdate: '2020-01-15 00:00:00',
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    id: 2,
                    category: 'Furto',
                    status: 'ongoing',
                    lastUpdate: '2020-01-18 00:00:00',
                    details: "Lorem ipsum calopsita dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    id: 3,
                    category: 'Vandalismo',
                    status: 'overdue',
                    lastUpdate: '2020-01-19 00:00:00',
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    id: 4,
                    category: 'Vandalismo',
                    status: 'overdue',
                    lastUpdate: '2020-01-19 00:00:00',
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    id: 5,
                    category: 'Vandalismo',
                    status: 'overdue',
                    lastUpdate: '2020-01-19 00:00:00',
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    id: 6,
                    category: 'Vandalismo',
                    status: 'overdue',
                    lastUpdate: '2020-01-19 00:00:00',
                    details: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                },
                {
                    id: 7,
                    category: 'Vandalismo',
                    status: 'overdue',
                    lastUpdate: '2020-01-19 00:00:00',
                    details: "Lorem freira ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui."
                }
            ],
        }
    },

    computed: {
        filteredIncidents: function () {
            const regex = new RegExp(this.switch1 + '|' + this.switch2 + '|'+ this.switch3)

            // First, filter by search
            let filtered = this.incidents.filter( (incident) => {
                return incident.category.toUpperCase().match(this.search.toUpperCase()) ||
                        incident.details.toUpperCase().match(this.search.toUpperCase())
            })

            // Then, filter by status using regex
            return filtered.filter( (incident) => {
                return incident.status.match(regex)
            })
        }
    },

    methods: {
        getColor(status) {
            if (status == 'ongoing') return '#2E86C1'
            else if (status == 'complete') return '#2BBD43'
            else if (status == 'overdue') return '#D8231B'
        },

        getOtherStatus(status) {
            let statusList= [];

            this.status.forEach( (s) => {
                if (s != status) statusList.push(s)
            })

            return statusList
        },

        addIncident() {
            // eslint-disable-next-line no-console
            console.log([this.newId, this.newCategory, this.newLastUpdate, 'overdue'])

            this.incidents.push({
                id: this.newId,
                category: this.newCategory,
                lastUpdate: this.newLastUpdate,
                status: 'overdue',
                details: 'ABORORIREO AODOSOO OALOHA ABOBRINHS'
            })

            this.newId++;
            this.newCategory = '';
            this.newLastUpdate = '';
            this.dialog = false
        },

        closeAddIncident() {
            this.dialog = false
        },

        testeCSS() {
            // Test if it's possible to use function to name CSS class
            return 'card'
        }
    }
}
</script>

<style scoped>
    .switch {
        width: 10%;
        height: 10%;
    }

    .search {
        width: 50%;
    }

    .statistics-layout {
        width: 100%;
    }

    .incidents-layout {
        width: 100%;
    }

    .incidents-card {
        width: 100%;
    }

    .incident.ongoing {
        border-left: 4px solid #2E86C1;
    }

    .incident.overdue {
        border-left: 4px solid #D8231B;
    }

    .incident.complete {
        border-left: 4px solid #2BBD43;
    }
</style>