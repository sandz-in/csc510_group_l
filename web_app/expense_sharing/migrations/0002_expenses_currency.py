# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('expense_sharing', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='expenses',
            name='currency',
            field=models.CharField(default='ss', max_length=8),
            preserve_default=False,
        ),
    ]
