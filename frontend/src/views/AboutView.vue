<template>
  <div class="about">
    <h1>This is an about page</h1>
    <br>
    <p>
      <GeoLink :lat="52.9849" :lon="9.5794">Visselhövede</GeoLink><br>
      <GeoLink :lat="52.9849" :lon="9.5794" :zoom="17">Visselhövede</GeoLink><br>
    </p>
    <AngebotListe/>
  </div>
</template>

<script setup lang="ts">
import GeoLink from '@/components/GeoLink.vue'
import AngebotListeItem from '@/components/AngebotListeItem.vue';
import AngebotListe from '@/components/AngebotListe.vue';
import { useFakeAngebot } from '@/services/useFakeAngebot';

import { ref } from 'vue';


</script>


<style>

</style>
