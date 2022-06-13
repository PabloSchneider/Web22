<template>
<input type="text" v-model="eingabe"/><br>
<button v-on:click="clear">clear</button><br>
<p v-for="angebot in filteredAngebote">
    <AngebotListeItem :a="angebot"></AngebotListeItem>
</p>
<button v-on:click="bietenSimulieren">bietenSimulieren</button>

</template>


<script setup lang="ts">

import AngebotListeItem from '@/components/AngebotListeItem.vue';
import { useFakeAngebot } from '@/services/useFakeAngebot';
import { computed } from '@vue/reactivity';
import { ref } from 'vue';
const {angebote, bietenSimulieren} = useFakeAngebot();
const eingabe = ref("");


const filteredAngebote = computed(
    ()=> angebote.value.filter(angebot => angebot.abholort.includes(eingabe.value) || angebot.beschreibung.includes(eingabe.value)||angebot.anbietername.includes(eingabe.value)));
console.log(eingabe.value)

function clear(){
    eingabe.value= "";
}
</script>