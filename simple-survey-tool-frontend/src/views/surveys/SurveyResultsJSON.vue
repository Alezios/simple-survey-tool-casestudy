<script setup lang="ts">
import { surveyApi } from '@/api/surveyApi';
import type { SurveyResponses } from '@/api/types/surveyresponse';
import 'survey-core/survey-core.css';
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { JsonViewer } from 'vue3-json-viewer';
import "vue3-json-viewer/dist/index.css";

const route = useRoute()
const surveyId = computed(() => route.params.id as string)

const surveyResults = ref<SurveyResponses>()
const loading = ref<boolean>(true)
const error = ref<string | null>(null)

onMounted(async () => {
    try {
        surveyResults.value = await surveyApi.getSurveyResponses(surveyId.value)
    } catch (err) {
        console.error('Failed to retrieve survey results:', err)
        alert("There was an error retrieving your survey results. Please try again.")
    } finally {
        loading.value = false
    }
})
</script>

<template>
    <div class="container mx-auto px-4 py-8">
        <div v-if="loading" class="text-center py-12">
            <p class="text-xl">Loading survey results...</p>
        </div>

        <div v-else-if="error" class="text-center py-12 text-red-500">
            <p class="text-xl">{{ error }}</p>
            <router-link to="/surveys" class="mt-4 inline-block text-blue-500 hover:underline">
                Back to surveys
            </router-link>
        </div>

        <div v-else-if="surveyResults">
            <div class="box">
                <JsonViewer :value="surveyResults" copyable boxed sort theme="light" />
            </div>
        </div>
    </div>
</template>
